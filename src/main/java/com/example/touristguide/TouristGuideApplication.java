package com.example.touristguide;

import com.example.touristguide.repository.TouristRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TouristGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(TouristGuideApplication.class, args);
        TouristRepository touristRepository = new TouristRepository();
        System.out.println(touristRepository.getAllAttractions());


    }

}
