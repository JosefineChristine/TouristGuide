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
    private String db_username = System.getenv("DB_USERNAME");
    //@Value("${spring.datasource.password}")
    private String db_password = System.getenv("DB_PASSWORD");

    private ArrayList<TouristAttraction> attractions = new ArrayList<>();

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    public TouristRepository(){
    }

    //***CRUD METHODS***------------------------------------------------------------------------------------------------
    //***GET***---------------------------------------------------------------------------------------------------------
    @Override
    public ArrayList<TouristAttraction> getAllAttractions(){
        ArrayList<TouristAttraction> attractions = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(db_url,db_username,db_password)){
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
        List<Tag> tags = new ArrayList<>();
        
        while (rs.next()) {
            String name = rs.getString("Name");

            if (name.equalsIgnoreCase(taPrevious.getName())) {  //vi skal lige forstå hvorfor og hvad der sker her?
                String tag = rs.getString("Tags");
                Tag tagEnum = Tag.setValue(tag);
                taPrevious.getTags().add(tagEnum);
            } else {
                
                String description = rs.getString("Description");
                String city = rs.getString("City");
                String tag = rs.getString("Tags");
                Tag tagEnum = Tag.setValue(tag);

                tags.add(tagEnum);
                taPrevious = new TouristAttraction(name,description,city,tags);
                attractions.add(taPrevious);
            }

        }
        return attractions;

    } catch (SQLException ignored){
        return attractions;
        }
    }

//    @Override
//    public ArrayList<TouristAttraction> getAllAttractions() {
//        ArrayList<TouristAttraction> attractions = new ArrayList<>();
//
//        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
//            String SQL = """
//            SELECT
//             TouristAttraction.Name AS Name,
//             TouristAttraction.Description AS Description,
//             TouristAttraction.ID AS ID,
//             City.Name AS City,
//             TouristAttraction_Tags.Tag_ID,
//             Tag.Name AS "Tag name"
//             FROM
//                 TouristAttraction
//             INNER JOIN
//                 City ON City.ID = TouristAttraction.City_ID
//             INNER JOIN
//                 TouristAttraction_Tags ON TouristAttraction_Tags.Attraction_ID = TouristAttraction.ID
//             INNER JOIN
//                 Tag ON Tag.ID = TouristAttraction_Tags.Tag_ID
//            ORDER BY
//                TouristAttraction.Name; """;
//
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(SQL);
//
//            TouristAttraction taPrevious = null;
//
//            while (rs.next()) {
//                String name = rs.getString("Name");
//                String description = rs.getString("Description");
//                String city = rs.getString("City");
//                String tag = rs.getString("Tag name");
//
//                // Check if the attraction has changed
//                if (taPrevious == null || !name.equalsIgnoreCase(taPrevious.getName())) {
//                    // If a new attraction, create a new list for tags
//                    List<Tag> tags = new ArrayList<>();
//                    if (tag != null) {
//                        Tag tagEnum = Tag.setValue(tag);
//                        tags.add(tagEnum);
//                    }
//                    taPrevious = new TouristAttraction(name, description, city, tags);
//                    attractions.add(taPrevious);
//                } else {
//                    // If the same attraction, just add the tag to the existing list
//                    if (tag != null) {
//                        Tag tagEnum = Tag.setValue(tag);
//                        taPrevious.getTags().add(tagEnum);
//                    }
//                }
//            }
//            return attractions;
//
//        } catch (SQLException e) {
//            // Log or handle the exception
//            e.printStackTrace();
//            return attractions;
//        }
//    }

    //***FIND***--------------------------------------------------------------------------------------------------------
    public TouristAttraction findAttractionByName(String name){
        TouristAttraction touristAttraction = null;
        for (TouristAttraction touristAttraction1 : attractions){
            if (touristAttraction1.getName().equalsIgnoreCase(name)){
                touristAttraction = touristAttraction1;
            }
        }
        return touristAttraction;
    }

    //***/attractions/add***--------------------------------------------------------------------------------------------
    public void addAttraction(TouristAttraction touristAttraction){
        getAllAttractions().add(touristAttraction);
    }

    //***/attractions/{name}/update***----------------------------------------------------------------------------------
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

    //***/attractions/{name}/remove***----------------------------------------------------------------------------------
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
