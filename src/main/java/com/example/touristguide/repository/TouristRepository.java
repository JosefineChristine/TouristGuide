package com.example.touristguide.repository;

import com.example.touristguide.model.Tag;
import com.example.touristguide.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TouristRepository implements ITouristRepository {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    //@Value("${spring.datasource.url}") // henter fra environment variables som er lagret i TouristGuideApplication
    private String db_url = System.getenv("DB_URL");
    //@Value("${spring.datasource.name}")
    private String db_username = System.getenv("DB_USER");
    //@Value("${spring.datasource.password}")
    private String db_password = System.getenv("DB_PASSWORD");


    private ArrayList<TouristAttraction> attractions = new ArrayList<>();

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    public TouristRepository(){
    }

    //--------------------------------------------***CRUD METHODS***----------------------------------------------------
    //***GET***---------------------------------------------------------------------------------------------------------
    @Override
    public ArrayList<TouristAttraction> getAllAttractions(){
        ArrayList<TouristAttraction> attractions = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            String SQL = """
            SELECT
             TouristAttraction.Name AS Name,
             TouristAttraction.Description AS Description,
             TouristAttraction.ID AS ID,
             City.Name AS City,
             TouristAttraction_Tags.Tag_ID,
             Tag.Name AS "Tag name"
             FROM
                 TouristAttraction
             INNER JOIN
                 City ON City.ID = TouristAttraction.City_ID
             INNER JOIN
                 TouristAttraction_Tags ON TouristAttraction_Tags.Attraction_ID = TouristAttraction.ID
             INNER JOIN
                 Tag ON Tag.ID = TouristAttraction_Tags.Tag_ID
            ORDER BY
                Name; """;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            TouristAttraction taPrevious = new TouristAttraction("","","",new ArrayList<>());

            while (rs.next()) {
                String name = rs.getString("Name");

                if (name.equalsIgnoreCase(taPrevious.getName())) {
                    // Samme attraction, tilføjer nyt tag til den eksisterende liste
                    String tag = rs.getString("Tag name");
                    Tag tagEnum = Tag.setValue(tag);
                    taPrevious.getTags().add(tagEnum);

                } else {
                    // ny attraction, resetter tags list
                    String description = rs.getString("Description");
                    String city = rs.getString("City");
                    String tag = rs.getString("Tag name");
                    Tag tagEnum = Tag.setValue(tag);

                    // opretter ny liste af tags for nye attraction
                    List<Tag> tags = new ArrayList<>();  // This ensures a new list for each attraction
                    tags.add(tagEnum);

                    // opdaterer taPrevious med ny attraction info
                    taPrevious = new TouristAttraction(name, description, city, tags);

                    attractions.add(taPrevious);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attractions;
    }


    //***FIND***--------------------------------------------------------------------------------------------------------
    public TouristAttraction findAttractionByName(String name){
        TouristAttraction touristAttraction = null;
        for (TouristAttraction touristAttraction1 : getAllAttractions()){
            if (touristAttraction1.getName().equalsIgnoreCase(name)){
                touristAttraction = touristAttraction1;
            }
        }
        return touristAttraction;
    }

    //***CREATE***------------------------------------------------------------------------------------------------------
    //TODO lav add metode
    public void addAttraction(TouristAttraction touristAttraction){
        String insertAttractionSQL = "INSERT INTO touristattraction (Name, Description, City) VALUES (?,?,?)";
        String selectTagIdSQL = "SELECT tag_id FROM tag WHERE name=?";
        String insertAttractionTagSQL = "INSERT INTO attraction_tags (attraction_id, tag_id) VALUES (?,?)";

        try (Connection con = DriverManager.getConnection(db_url,db_username,db_password)){
            int attractionId = 0;
            con.setAutoCommit(false); // transaction begin

            //indsæt attraktion  (hvordan ved vi at det er en ny attraktion vi opretter?)
            try(PreparedStatement attractionStatement = con.prepareStatement(insertAttractionSQL, Statement.RETURN_GENERATED_KEYS)){
            attractionStatement.setString(1,touristAttraction.getName());
            attractionStatement.setString(2, touristAttraction.getDescription());
            attractionStatement.setString(3, touristAttraction.getCity());
            attractionStatement.executeUpdate();

            //Få det genererede attraction_id
                ResultSet generatedKeys = attractionStatement.getGeneratedKeys();
                if(generatedKeys.next()){
                    attractionId = generatedKeys.getInt(1);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            //indsæt tags i attraction_tags tabellen
            for(Tag tag : touristAttraction.getTags()){
                int tagId = 0;
                String tagString = tag.getTagName();

                // Hent tag_id fra tag-tabellen
                try (PreparedStatement tagStatement = con.prepareStatement(selectTagIdSQL)){
                    tagStatement.setString(1,tagString);
                    ResultSet tagResultSet = tagStatement.executeQuery();

                    if(tagResultSet.next()){
                        tagId = tagResultSet.getInt("tag_id");
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }

                //Indsæt i attraction_tags
                try (PreparedStatement attractionTagStatement = con.prepareStatement(insertAttractionTagSQL)){
                   attractionTagStatement.setInt(1, attractionId);
                   attractionTagStatement.setInt(2, tagId);
                   attractionTagStatement.executeUpdate();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            con.commit(); //transaction end

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //***UPDATE***------------------------------------------------------------------------------------------------------
    //TODO lav update til sidst!
    public void updateAttraction(TouristAttraction touristAttraction) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equals(touristAttraction.getName())) {
                attraction.setName(touristAttraction.getName());
                attraction.setDescription(touristAttraction.getDescription());
                attraction.setCity(touristAttraction.getCity());
                attraction.setTags(touristAttraction.getTags());
                return;
            }
        }
    }

    //***DELETE***------------------------------------------------------------------------------------------------------
    //TODO lav remove metode
    public void removeAttraction(TouristAttraction touristAttraction){
        attractions.remove(touristAttraction);
    }

    //***attractions/{name)/tags***-------------------------------------------------------------------------------------
    public List<Tag> getTagsFromAttraction(String name){
        List<Tag> tagsFromAttraction = new ArrayList<>();
        for (TouristAttraction touristAttraction : attractions){
            if(touristAttraction.getName().equalsIgnoreCase(name)) {
                tagsFromAttraction.addAll(touristAttraction.getTags());
            }
        }
            return  tagsFromAttraction;
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
