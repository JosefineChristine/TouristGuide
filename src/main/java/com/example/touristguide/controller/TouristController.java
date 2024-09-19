package com.example.touristguide.controller;

import com.example.touristguide.model.Tag;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.service.TouristService;
import org.springframework.boot.Banner;
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
        return "attractionDetail"; //TODO lave ny HTML side til get name?
    }

    //TODO get attractions/{name}/tags
    @GetMapping("/{name}/tags")
    public String getTagsFromAttraction(@RequestParam("name") String name, Model model){
        List<Tag> tagsFromAttraction = touristService.getTagsFromAttraction(name);
        model.addAttribute("tagsFromAttraction", tagsFromAttraction);
        return "tags";
    }

    //***POST MAPPING METHODS***----------------------------------------------------------------------------------------
    @GetMapping("/add")
    public String addCocktail(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.addAttraction(touristAttraction);
        return "redirect:/addAttraction";
    } //TODO lave HTML side til add

    //TODO @PostMapping("/save")

//    TODO @GetMapping("/{name}/edit")
//    public String updateAttraction(@PathVariable String searchName, @RequestBody TouristAttraction touristAttraction){
//        TouristAttraction newAttraction = touristService.updateAttraction(searchName, touristAttraction);
//        return ;
//}

    //TODO @PostMapping ("/update")


    @PostMapping("/delete/{searchName}")
    public ResponseEntity<HttpStatus> removeAttraction(@PathVariable String searchName) {
        touristService.removeAttraction(searchName);
        return new ResponseEntity<>(HttpStatus.GONE);
    } //TODO rette delete til at returnere en HTML side
    //TODO kan ikke kalde på touristService?

    //***END***---------------------------------------------------------------------------------------------------------
}