package com.example.touristguide.repository;

import com.example.touristguide.model.Tag;
import com.example.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@Repository
public class TouristRepository {

    ArrayList<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository(){
        populateAttractions();
    }

    public void populateAttractions(){
        attractions.add(new TouristAttraction("Den lille havfrue", "En lille havfrue", "København", (ArrayList<Tag>) Arrays.asList(Tag.STATUE, Tag.SEVÆRDIGHED)));
        attractions.add(new TouristAttraction("Rundetårn", "Et højt rundt tårn", "København",(ArrayList<Tag>) Arrays.asList(Tag.SEVÆRDIGHED, Tag.UNDERHOLDNING)));
        attractions.add(new TouristAttraction("Tivoli", "En forlystelsespark", "København", (ArrayList<Tag>) Arrays.asList(Tag.FORLYSTELSESPARK, Tag.UNDERHOLDNING)));
    }

    //TODO lave liste af tags om så der ikke skal castes?

    public ArrayList<TouristAttraction> getAllAttractions(){
        return attractions;
    }

    public TouristAttraction findAttractionByName(String name){
        for (TouristAttraction touristAttraction : attractions){
            if (touristAttraction.getName().equalsIgnoreCase(name)){
                return touristAttraction;
            }
        }
        return null;
    }

    public TouristAttraction addAttraction(TouristAttraction touristAttraction){
        getAllAttractions().add(touristAttraction);
        return touristAttraction;
    }

    public TouristAttraction updateAttraction(String searchName, TouristAttraction touristAttraction){
        for (TouristAttraction attraction : attractions){
            if (attraction.getName().equalsIgnoreCase(searchName)){
                attraction.setName(touristAttraction.getName());
                attraction.setDescription(touristAttraction.getDescription());
                return attraction;
            }
        }
        return null;
    }

    public void removeAttraction(String searchName){
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(searchName)) {
                attractions.remove(attraction);
            }
        }
    }

}
