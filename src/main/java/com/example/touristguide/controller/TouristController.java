package com.example.touristguide.controller;

import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("attractions")
public class TouristController {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final TouristService touristService;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    //***GET MAPPING METHODS***-----------------------------------------------------------------------------------------
    @GetMapping("")
    public String getAllAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";
    }

    @GetMapping("/{name}")
    public String findAttractionByName(@RequestParam("name") String name, Model model) {
        TouristAttraction touristAttraction = touristService.findAttractionByName(name);
        model.addAttribute("touristAttraction", touristAttraction);
        return "attractionList"; //TODO lave ny HTML side til get name?
    }

    //TODO get attractions/{name}/tags

    //***POST MAPPING METHODS***----------------------------------------------------------------------------------------
    @PostMapping("/add")
    public String addCocktail(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.addAttraction(touristAttraction);
        return "redirect:/addTouristAttraction";
    } //TODO lave HTML side til add

    //TODO save: POST endpoint

    //TODO edit: GET endpoint som kan edit

//    @GetMapping("/{name}/edit")
//    public String updateAttraction(@PathVariable String searchName, @RequestBody TouristAttraction touristAttraction){
//        TouristAttraction newAttraction = touristService.updateAttraction(searchName, touristAttraction);
//        return ;
//}

    //@PostMapping ("/update")

    //TODO update skal ændres til at gemme ændringer


    @PostMapping("/delete/{searchName}")
    public ResponseEntity<HttpStatus> removeAttraction(@PathVariable String searchName) {
        touristService.removeAttraction(searchName);
        return new ResponseEntity<>(HttpStatus.GONE);
    } //TODO rette delete til at returnere en HTML side
    //TODO kan ikke kalde på touristService?

    //***END***---------------------------------------------------------------------------------------------------------
}