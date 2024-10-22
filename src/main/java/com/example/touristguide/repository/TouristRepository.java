package com.example.touristguide.repository;

import com.example.touristguide.model.Tag;
import com.example.touristguide.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TouristRepository {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.name}")
    private String db_username;
    @Value("${spring.datasource.password}")
    private String db_password;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
//    public TouristRepository(){
//        populateAttractions();
//    }

    //***METHODS***-----------------------------------------------------------------------------------------------------

    //***GET***------------------------------------------------------------------------------------------------
    @Override
    public ArrayList<TouristAttraction> getAllAttractions(){
    ArrayList<TouristAttraction> attractions = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(db_url,db_username,db_password)){
        String SQL = """
                    SELECT 
                            TouristAttraction.Name, TouristAttraction.Description, TouristAttraction.Price, TouristAttraction.ID, City.Name AS City 
                    FROM TouristAttraction INNER JOIN City ON City.ID = TouristAttraction.City_ID""";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while (rs.next()){
            String name = rs.getString("Name");
            String description = rs.getString("Description");
            int price = rs.getInt("Price");
            String city = rs.getString("City"); // vi skal have city
            List<Tag> tags = rs.getArray("Tags"); // vi skal have fundet alle tags
            attractions.add(new TouristAttraction(name,description,price,city,tags));
        }
        return attractions;

    } catch (SQLException e){
        throw new RuntimeException(e);
        }
    }

//    public TouristAttraction findAttractionByName(String name){
//        TouristAttraction touristAttraction = null;
//        for (TouristAttraction touristAttraction1 : attractions){
//            if (touristAttraction1.getName().equalsIgnoreCase(name)){
//                touristAttraction = touristAttraction1;
//            }
//        }
//        return touristAttraction;
//    }
//
//    //***/attractions/add***--------------------------------------------------------------------------------------------
//    public void addAttraction(TouristAttraction touristAttraction){
//        getAllAttractions().add(touristAttraction);
//    }
//
//    //***/attractions/{name}/update***----------------------------------------------------------------------------------
//    public void updateAttraction(TouristAttraction touristAttraction) {
//        for (TouristAttraction attraction : attractions) {
//            if (attraction.getName().equals(touristAttraction.getName())) {
//                attraction.setName(touristAttraction.getName());
//                attraction.setDescription(touristAttraction.getDescription());
//                attraction.setCity(touristAttraction.getCity());
//                attraction.setTags(touristAttraction.getTags());
//                return;
//            }
//        }
//    }
//
//    //***/attractions/{name}/remove***----------------------------------------------------------------------------------
//    public void removeAttraction(TouristAttraction touristAttraction){
//        attractions.remove(touristAttraction);
//    }
//
//    //***attractions/{name)/tags***-------------------------------------------------------------------------------------
//    public List<Tag> getTagsFromAttraction(String name){
//        List<Tag> tagsFromAttraction = new ArrayList<>();
//        for (TouristAttraction touristAttraction : attractions){
//            if(touristAttraction.getName().equalsIgnoreCase(name)) {
//                tagsFromAttraction.addAll(touristAttraction.getTags());
//            }
//        }
//            return  tagsFromAttraction;
//    }

    //***END***---------------------------------------------------------------------------------------------------------
}
