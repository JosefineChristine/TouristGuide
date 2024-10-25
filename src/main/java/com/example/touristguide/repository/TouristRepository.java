package com.example.touristguide.repository;

import com.example.touristguide.model.Tag;
import com.example.touristguide.model.TouristAttraction;
import com.mysql.cj.protocol.Resultset;
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
        System.out.println("getAllAttractions");
        ArrayList<TouristAttraction> attractions = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            String SQL = """
            SELECT
             TouristAttraction.touristAttraction_name AS Name,
             TouristAttraction.touristAttraction_description AS Description,
             TouristAttraction.touristAttraction_id AS ID,
             City.city_name AS City,
             TouristAttraction_Tags.tag_id AS "Tag ID",
             Tag.tag_name AS "Tag name"
             FROM
                 TouristAttraction
             INNER JOIN
                 City ON city.city_id = TouristAttraction.city_id
             INNER JOIN
                 TouristAttraction_Tags ON TouristAttraction_Tags.touristAttraction_id = TouristAttraction.touristAttraction_id
             INNER JOIN
                 Tag ON Tag.tag_id = TouristAttraction_Tags.tag_id
            ORDER BY
                Name; """;
            Statement stmt = con.createStatement(); //Opretter et statement til at udføre SQL-forespørgsler
            ResultSet rs = stmt.executeQuery(SQL); //Udfører en SELECT-forespørgsel og returnerer resultaterne som et ResultSet.

            TouristAttraction taPrevious = new TouristAttraction("","","",new ArrayList<>());

            while (rs.next()) {
                String name = rs.getString("Name");

                if (name.equalsIgnoreCase(taPrevious.getName())) {
                    // Samme attraction, tilføjer nyt tag til den eksisterende liste
                    String tag = rs.getString("Tag name");
                    taPrevious.getTags().add(tag);

                } else {
                    // ny attraction, resetter tags list
                    String description = rs.getString("Description");
                    String city = rs.getString("City");
                    String tag = rs.getString("Tag name");

                    // opretter ny liste af tags for nye attraction
                    List<String> tags = new ArrayList<>();  // This ensures a new list for each attraction
                    tags.add(tag);

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
    public void addAttraction(TouristAttraction touristAttraction){
        System.out.println("Add attraction");
        String getCityIdSQL ="SELECT city_id FROM City WHERE city_name = ?";
        String insertAttractionSQL = "INSERT INTO touristattraction (touristAttraction_name, touristAttraction_description, city_id) VALUES (?,?,?)";
        String selectTagIdSQL = "SELECT tag_id FROM Tag WHERE tag_name=?";
        String insertAttractionTagSQL = "INSERT INTO touristAttraction_tags (touristAttraction_id, tag_id) VALUES (?,?)";

        try (Connection con = DriverManager.getConnection(db_url,db_username,db_password)){

            //con.setAutoCommit(false);
            //transaction begin

            // gette city id
            PreparedStatement preparedStatement1 = con.prepareStatement(getCityIdSQL);
            preparedStatement1.setString(1, touristAttraction.getCity());
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int cityId = resultSet1.getInt("city_id");

            //indsæt attraktion  (hvordan ved vi at det er en ny attraktion vi opretter?)
            PreparedStatement attractionStatement = con.prepareStatement(insertAttractionSQL, Statement.RETURN_GENERATED_KEYS);
            attractionStatement.setString(1,touristAttraction.getName());
            attractionStatement.setString(2, touristAttraction.getDescription());
            attractionStatement.setInt(3, cityId);
            int rowsInserted = attractionStatement.executeUpdate();
            //Få det genererede attraction_id
            ResultSet generatedKeys = attractionStatement.getGeneratedKeys();
            int attractionId = -1;
            if(generatedKeys.next()){
                attractionId = generatedKeys.getInt(1);
            }

            //indsæt tags i attraction_tags tabellen
            for(String tag : touristAttraction.getTags()){
                int tagId = -1;

                // Hent tag_id fra tag-tabellen
                PreparedStatement tagStatement = con.prepareStatement(selectTagIdSQL);
                tagStatement.setString(1,tag);
                ResultSet tagResultSet = tagStatement.executeQuery();
                if(tagResultSet.next()){
                    tagId = tagResultSet.getInt(1);
                }

                //Indsæt i attraction_tags
                PreparedStatement attractionTagStatement = con.prepareStatement(insertAttractionTagSQL);
                attractionTagStatement.setInt(1, attractionId);
                attractionTagStatement.setInt(2, tagId);
                attractionTagStatement.executeUpdate();

            }

            //con.commit(); //transaction end

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<String> getAllCities(){
        String citySQL = "SELECT * FROM City";
        List<String> cities = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(db_url,db_username,db_password)){
            ResultSet rs = con.createStatement().executeQuery(citySQL);


            while (rs.next()){
                cities.add(rs.getString("city_name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return cities;
    }

    public List<String> getAllTags(){
        String tagSQL = "SELECT * FROM Tag";
        List<String> tags = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(db_url,db_username,db_password)){
            ResultSet rs = con.createStatement().executeQuery(tagSQL);


            while (rs.next()){
                tags.add(rs.getString("tag_name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return tags;
    }

    //***UPDATE***------------------------------------------------------------------------------------------------------
    //TODO lav update til sidst!
    public void updateAttraction(TouristAttraction touristAttraction) {
        removeAttraction(touristAttraction);
        addAttraction(touristAttraction);
    }

    //***DELETE***------------------------------------------------------------------------------------------------------
    public void removeAttraction(TouristAttraction touristAttraction){
        //attractions.remove(touristAttraction);
        String deleteSQL = "DELETE FROM Touristattraction WHERE touristAttraction_name = ?";

        try(Connection con = DriverManager.getConnection(db_url,db_username,db_password)){
            PreparedStatement prepstmt = con.prepareStatement(deleteSQL);
            prepstmt.setString(1, touristAttraction.getName());
            prepstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    //***attractions/{name)/tags***-------------------------------------------------------------------------------------
    public List<String> getTagsFromAttraction(String name){
        List<String> tagsFromAttraction = new ArrayList<>();
        for (TouristAttraction touristAttraction : attractions){
            if(touristAttraction.getName().equalsIgnoreCase(name)) {
                tagsFromAttraction.addAll(touristAttraction.getTags());
            }
        }
            return  tagsFromAttraction;
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
