package com.example.touristguide.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TouristAttraction {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private String name;
    private String description;
    private String city;
    private List<Tag> tags = new ArrayList<>();

    //***CONSTRUCTORS***------------------------------------------------------------------------------------------------
    public TouristAttraction(){
    }

    public TouristAttraction(String name, String description, String city, List<Tag> tags) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }

    //***GETTER & SETTER METHODS***-------------------------------------------------------------------------------------
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public List<Tag> getTags(){
        return tags;
    }

    public void setTags(List<Tag> tags){
        this.tags = tags;
    }

    //***EQUALS & HASHCODE(for testing)***------------------------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TouristAttraction that = (TouristAttraction) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(city, that.city) && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, city, tags);
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
