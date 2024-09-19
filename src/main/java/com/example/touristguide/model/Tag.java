package com.example.touristguide.model;

public enum Tag {

    //***ENUM***--------------------------------------------------------------------------------------------------------
    STATUE          ("Statue"),
    SEVÆRDIGHED     ("Seværdighed"),
    FORLYSTELSESPARK("Forlystelsespark"),
    UNDERHOLDNING   ("Underholdning"),
    RESTAURANT      ("Restaurant"),
    KONCERT         ("Koncert"),
    TEATER          ("Teater"),
    NATUR           ("Natur"),
    MUSEUM          ("Museum"),
    SPORT           ("Sport"),
    PARK            ("Park"),
    DESIGN          ("Design"),
    ARKITEKTUR      ("Arkitektur"),
    MONUMENTER      ("Monumenter");

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final String displayName;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    //Enum constructor må ikke være public af en eller anden årsag...
    Tag(String displayName){
        this.displayName = displayName;
    }

    //***GETTER METHOD***-----------------------------------------------------------------------------------------------
    public String getDisplayName(){
        return displayName;
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
