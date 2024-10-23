package com.example.touristguide.model;

import java.util.ArrayList;
import java.util.List;

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
    MONUMENTER      ("Monumenter"),
    KUNST           ("Kunst"),
    HISTORIE        ("Historie");

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



    public static Tag setValue(String tag) {
        for (Tag t : Tag.values()) {
            if (t.name().equalsIgnoreCase(tag)) {
                return t;
            }
        }
        throw new IllegalArgumentException("No enum constant " + tag);
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
