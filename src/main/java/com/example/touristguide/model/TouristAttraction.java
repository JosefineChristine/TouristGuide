package com.example.touristguide.model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {

    private String name;
    private String description;
    private String city;
    private List<Tag> tags = new ArrayList<>();

    public TouristAttraction(){
    }

    public TouristAttraction(String name, String description, String city, ArrayList<Tag> tags) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }

    //TODO getter og setter til tags og city

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }


}
