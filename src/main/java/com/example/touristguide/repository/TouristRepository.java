package com.example.touristguide.repository;

import com.example.touristguide.model.Tag;
import com.example.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TouristRepository {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    ArrayList<TouristAttraction> attractions = new ArrayList<>();

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    public TouristRepository(){
        populateAttractions();
    }

    //***METHODS***-----------------------------------------------------------------------------------------------------
    public void populateAttractions(){
        attractions.add(new TouristAttraction("Den lille havfrue", "En lille havfrue", "København", Arrays.asList(Tag.STATUE, Tag.SEVÆRDIGHED)));
        attractions.add(new TouristAttraction("Rundetårn", "Et højt rundt tårn", "København",Arrays.asList(Tag.SEVÆRDIGHED, Tag.UNDERHOLDNING)));
        attractions.add(new TouristAttraction("Tivoli", "En forlystelsespark", "København", Arrays.asList(Tag.FORLYSTELSESPARK, Tag.UNDERHOLDNING)));
    }

    //***/attractions
    public ArrayList<TouristAttraction> getAllAttractions(){
        return attractions;
    }

    public TouristAttraction findAttractionByName(String name){
        TouristAttraction touristAttraction = null;
        for (TouristAttraction touristAttraction1 : attractions){
            if (touristAttraction1.getName().equalsIgnoreCase(name)){
                touristAttraction = touristAttraction1;
            } else {
                return null;
            }
        }
        return touristAttraction;
    }

    //***/attractions/add***--------------------------------------------------------------------------------------------
    public TouristAttraction addAttraction(TouristAttraction touristAttraction){
        getAllAttractions().add(touristAttraction);
        return touristAttraction;
    }

    //***/attractions/{name}/update
    //Skal der et TouristAttraction objekt ind i parameteret?
    public TouristAttraction updateAttraction(String searchName, TouristAttraction touristAttraction){
        for (TouristAttraction attraction : attractions){
            if (attraction.getName().equalsIgnoreCase(searchName)){
                attraction.setName(touristAttraction.getName());
                attraction.setDescription(touristAttraction.getDescription());
                attraction.setCity(touristAttraction.getCity());
                attraction.setTags(touristAttraction.getTags());
                return attraction;
            }
        }
        return null;
    }

//    public TouristAttraction updateAttraction(String searchName){
//        TouristAttraction touristAttraction = new TouristAttraction();
//        for (TouristAttraction attraction : attractions){
//            if (attraction.getName().equalsIgnoreCase(searchName)){
//                touristAttraction.setName(touristAttraction.getName());
//                touristAttraction.setDescription(touristAttraction.getDescription());
//                touristAttraction.setCity(touristAttraction.getCity());
//                touristAttraction.setTags(touristAttraction.getTags());
//                attractions.remove(attraction);
//                attractions.add(touristAttraction);
//            }
//        }
//        return touristAttraction;
//    }

    public void removeAttraction(String searchName){
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(searchName)) {
                attractions.remove(attraction);
            }
        }
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
