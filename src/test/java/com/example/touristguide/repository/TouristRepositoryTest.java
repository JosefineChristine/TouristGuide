package com.example.touristguide.repository;

import com.example.touristguide.controller.TouristController;
import com.example.touristguide.model.Tag;
import com.example.touristguide.model.TouristAttraction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TouristRepositoryTest {

    private TouristRepository touristRepository = new TouristRepository();

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



}