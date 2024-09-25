package com.example.touristguide.repository;

import com.example.touristguide.model.Tag;
import com.example.touristguide.model.TouristAttraction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TouristRepositoryTest {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final TouristRepository touristRepository = new TouristRepository();

    //***TEST METHODS***------------------------------------------------------------------------------------------------
    @Test
    void getAllAttractions() {
        //arrange
        List<TouristAttraction> expectedAttractions = new ArrayList<>();
        expectedAttractions.add(new TouristAttraction("Den lille havfrue", "Staute af den lille havfrue", "København, Indre By", Arrays.asList(Tag.STATUE, Tag.SEVÆRDIGHED)));
        expectedAttractions.add(new TouristAttraction("Rundetårn", "Et højt rundt tårn", "København, Indre By",Arrays.asList(Tag.SEVÆRDIGHED, Tag.UNDERHOLDNING, Tag.ARKITEKTUR)));
        expectedAttractions.add(new TouristAttraction("Dyrehavsbakken", "En forlystelsespark og en park ude i naturen", "Klampenborg", Arrays.asList(Tag.FORLYSTELSESPARK, Tag.UNDERHOLDNING, Tag.NATUR, Tag.PARK)));
        expectedAttractions.add(new TouristAttraction("Experimentarium", "Et interaktivt museeum", "København, Hellerup", Arrays.asList(Tag.MUSEUM, Tag.UNDERHOLDNING)));
        expectedAttractions.add(new TouristAttraction("Statens Museum for Kunst", "Kunst museum", "København, Indre By", Arrays.asList(Tag.MUSEUM, Tag.KUNST, Tag.HISTORIE)));
        expectedAttractions.add(new TouristAttraction("Legoland", "En forlystelsespark", "Billund", Arrays.asList(Tag.FORLYSTELSESPARK, Tag.UNDERHOLDNING)));

        //act
        List<TouristAttraction> actualAttractions = touristRepository.getAllAttractions();

        //assert
        assertEquals(expectedAttractions, actualAttractions);
    }

    @Test
    void findAttractionByName(){
        //arrange
        String actualName = "Rundetårn";

        //act
        TouristAttraction TouristAttraction = touristRepository.findAttractionByName("Rundetårn");
        String expectedName = TouristAttraction.getName();

        //assert
        assertEquals(actualName, expectedName);
    }

    @Test
    void addAttraction(){
        //arrange
        ArrayList<TouristAttraction> expectedAttractions = new ArrayList<>();
        TouristAttraction touristAttraction = new TouristAttraction("Test", "Test beskrivelse", "Test by", Arrays.asList(Tag.STATUE, Tag.ARKITEKTUR));

        //act
        touristRepository.addAttraction(touristAttraction);
        String actualName = "";
        String expectedName = "Test";
            for(TouristAttraction touristAttraction2 : touristRepository.attractions){
                if(touristAttraction2.getName().equalsIgnoreCase("Test")){
                actualName = touristAttraction2.getName();
                }
            }

        //assert
        assertEquals(actualName, expectedName);
    }

    @Test
    void removeAttraction(){
        //arrange
        TouristAttraction touristAttraction = new TouristAttraction("Test", "Test beskrivelse", "Test by", Arrays.asList(Tag.STATUE, Tag.ARKITEKTUR));
        TouristAttraction touristAttraction1 = new TouristAttraction();

        //act
        touristRepository.addAttraction(touristAttraction);
        touristRepository.removeAttraction(touristAttraction);
        String expectedName = null;
        for(TouristAttraction ta : touristRepository.attractions){
            if(ta.getName().equals("Test")){
                expectedName = ta.getName();
                touristAttraction1 = touristAttraction;
            }
        }

        //assert
        assertEquals(expectedName, touristAttraction1.getName());
    }

    @Test
    void updateAttraction() {
    // Arrange
    TouristAttraction updatedAttraction = new TouristAttraction("Den lille havfrue", "Test beskrivelse", "Test by", Arrays.asList(Tag.STATUE, Tag.ARKITEKTUR));

    // Act
    touristRepository.updateAttraction(updatedAttraction);
    TouristAttraction actualAttraction = touristRepository.findAttractionByName("Den lille havfrue");

    // Assert
    assertEquals(updatedAttraction.getDescription(), actualAttraction.getDescription());
    assertEquals(updatedAttraction.getCity(), actualAttraction.getCity());
    assertEquals(updatedAttraction.getTags(), actualAttraction.getTags());
    }

    @Test
    void getTagsFromAttraction(){
        //arrange
        int expectedSize = 2; //size fordi 'Den lille Havfrue' har 2 tags

        //act
        int actualSize = touristRepository.getTagsFromAttraction("Den lille havfrue").size();

        //assert
        assertEquals(expectedSize, actualSize);
    }

    //***END***---------------------------------------------------------------------------------------------------------
}