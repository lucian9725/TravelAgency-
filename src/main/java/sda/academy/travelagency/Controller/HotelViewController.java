package sda.academy.travelagency.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sda.academy.travelagency.Service.service.HotelService;

@Controller
public class HotelViewController {

    private final HotelService hotelService;

    public HotelViewController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels-view")
    public String showHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "hotels"; // Thymeleaf template
    }
}
