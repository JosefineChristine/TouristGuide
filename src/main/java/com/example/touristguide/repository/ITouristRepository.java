package com.example.touristguide.repository;

import com.example.touristguide.model.TouristAttraction;

import java.util.ArrayList;

public interface ITouristRepository {

    ArrayList<TouristAttraction> getAllAttractions();

}
