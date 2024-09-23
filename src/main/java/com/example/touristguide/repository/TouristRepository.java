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

    //***/attractions***------------------------------------------------------------------------------------------------
    public ArrayList<TouristAttraction> getAllAttractions(){
        return attractions;
    }

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
                break;
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
