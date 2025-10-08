package sda.academy.travelagency.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.academy.travelagency.Dto.BuyOrderRequest;
import sda.academy.travelagency.Entity.Location;
import sda.academy.travelagency.Entity.Tour;
import sda.academy.travelagency.Service.service.LocationService;
import sda.academy.travelagency.Service.service.OrderService;
import sda.academy.travelagency.Service.service.TourService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RequestMapping("/tours-view")
public class TourViewController {

    private final TourService tourService;
    private final OrderService orderService;
    private final LocationService locationService;

    public TourViewController(TourService tourService, OrderService orderService, LocationService locationService) {
        this.tourService = tourService;
        this.orderService = orderService;
        this.locationService = locationService;
    }

    @GetMapping
    public String showTours(Model model) {
        model.addAttribute("tours", tourService.getAllTours());
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("buyOrderRequest", new BuyOrderRequest());
        return "tours-view";
    }

    // Primeste parametri simpli din formular si construieste Tour
    @PostMapping("/add")
    public String addTour(
            @RequestParam Integer whereFromId,
            @RequestParam Integer whereToId,
            @RequestParam String departureDate,
            @RequestParam String dateOfReturn,
            @RequestParam int numberOfDay,
            @RequestParam double priceForAdults,
            @RequestParam double priceForChildren,
            @RequestParam int numberOfAdults,
            @RequestParam int numberOfChildren,
            Model model
    ) {
        try {
            Location from = locationService.getLocationById(whereFromId);
            Location to = locationService.getLocationById(whereToId);

            Tour tour = new Tour();
            tour.setWhereFrom(from);
            tour.setWhereTo(to);

            // parse date
            try {
                tour.setDepartureDate(LocalDate.parse(departureDate));
                tour.setDateOfReturn(LocalDate.parse(dateOfReturn));
            } catch (DateTimeParseException e) {
                model.addAttribute("errorMessage", "Format dată invalid. Folosește YYYY-MM-DD.");
                model.addAttribute("tours", tourService.getAllTours());
                model.addAttribute("locations", locationService.getAllLocations());
                return "tours-view";
            }

            tour.setNumberOfDay(numberOfDay);
            tour.setPriceForAdults(priceForAdults);
            tour.setPriceForChildren(priceForChildren);
            tour.setNumberOfAdults(numberOfAdults);
            tour.setNumberOfChildren(numberOfChildren);

            tourService.addTour(tour);

            model.addAttribute("successMessage", "Tur adăugat cu succes!");
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Eroare la adăugare tur: " + ex.getMessage());
        }

        model.addAttribute("tours", tourService.getAllTours());
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("buyOrderRequest", new BuyOrderRequest());
        return "tours-view";
    }

    // Formular cumpărare: folosește OrderService existent
    @PostMapping("/buy")
    public String buyTour(
            @RequestParam Integer tourId,
            @RequestParam int numAdults,
            @RequestParam int numChildren,
            @RequestParam String customerName,
            Model model
    ) {
        try {
            orderService.buyTour(tourId, numAdults, numChildren, customerName);
            model.addAttribute("successMessage", "Comanda a fost plasată cu succes!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Eroare la cumpărare: " + e.getMessage());
        }

        model.addAttribute("tours", tourService.getAllTours());
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("buyOrderRequest", new BuyOrderRequest());
        return "tours-view";
    }
}
