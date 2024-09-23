package com.example.touristguide.service;

import com.example.touristguide.model.Tag;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TouristService {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final TouristRepository touristRepository;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    public TouristService(TouristRepository touristRepository){
        this.touristRepository = touristRepository;
    }

    //***METHODS***-----------------------------------------------------------------------------------------------------
    public ArrayList<TouristAttraction> getAllAttractions(){
        return touristRepository.getAllAttractions();
    }

    public TouristAttraction findAttractionByName(String name) {
        return touristRepository.findAttractionByName(name);
    }

    public void addAttraction(TouristAttraction touristAttraction){
                touristRepository.addAttraction(touristAttraction);
    }

    public void updateAttraction(TouristAttraction touristAttraction){
                touristRepository.updateAttraction(touristAttraction);
    }

    public void removeAttraction(TouristAttraction touristAttraction){
                touristRepository.removeAttraction(touristAttraction);
    }

    public List<Tag> getTagsFromAttraction(String name){
        return touristRepository.getTagsFromAttraction(name);
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
