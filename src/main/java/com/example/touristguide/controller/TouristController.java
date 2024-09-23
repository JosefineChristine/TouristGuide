package com.example.touristguide.controller;
import com.example.touristguide.model.Tag;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("attractions")
public class    TouristController {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final TouristService touristService;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    //***MAPPING METHODS***---------------------------------------------------------------------------------------------
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
        return "attractionDetail";
    }

    @GetMapping("/{name}/tags")
    public String getTagsFromAttraction(@PathVariable("name") String name, Model model){
        List<Tag> tagsFromAttraction = touristService.getTagsFromAttraction(name);
        model.addAttribute("tagsFromAttraction", tagsFromAttraction);
        return "tags";
    }

    @GetMapping("/add")
    public String addAttraction(Model model) {
        TouristAttraction touristAttraction = new TouristAttraction();
        model.addAttribute("touristAttraction", touristAttraction);
        model.addAttribute("tags", Arrays.asList(Tag.values()));
        return "addAttraction";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction touristAttraction){
        touristService.addAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    //***(/update)***---------------------------------------------------------------------------------------------------
    @GetMapping("/{name}/edit")
    public String AttractionEdit(@PathVariable("name") String name, Model model){
        TouristAttraction touristAttraction = touristService.findAttractionByName(name);
        model.addAttribute("touristAttraction", touristAttraction);
        model.addAttribute("name", touristAttraction.getName());
        model.addAttribute("description", touristAttraction.getDescription());
        model.addAttribute("city", touristAttraction.getCity());
        model.addAttribute("tags", Arrays.asList(Tag.values()));
        return "updateAttraction";
    }

    @PostMapping("/{name}/update")
    public String updateAttraction(@PathVariable("name") String name, @ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    //***(/remove)***---------------------------------------------------------------------------------------------------
    @PostMapping("/{name}/remove")
    public String RemoveAttraction(@PathVariable String name){
        TouristAttraction touristAttraction = touristService.findAttractionByName(name);
        touristService.removeAttraction(touristAttraction);
        return "redirect:/attractions";
    }
    
    //***END***---------------------------------------------------------------------------------------------------------
}