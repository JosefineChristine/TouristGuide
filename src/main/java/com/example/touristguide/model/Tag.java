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
    MONUMENTER      ("Monumenter"),
    KUNST           ("Kunst"),
    HISTORIE        ("Historie");

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final String tagName;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    //Enum constructor må ikke være public af en eller anden årsag...
    Tag(String tagName){
        this.tagName = tagName;
    }

    //***GETTER METHOD***-----------------------------------------------------------------------------------------------
    public String getTagName(){
        return tagName;
    }

    //***SETTER METHODS***----------------------------------------------------------------------------------------------
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
