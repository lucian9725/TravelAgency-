package sda.academy.travelagency.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location whereFrom;

    @OneToOne
    @JoinColumn(name = "location2_id")
    private Location whereTo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfReturn;

    @Column
    private int numberOfDay;

    @Column
    private double priceForAdults;

    @Column
    private double priceForChildren;

    @Column
    private int numberOfAdults;

    @Column
    private int numberOfChildren;

    @OneToMany(mappedBy = "tour")
    @JsonIgnore
    private List<Order> orders;




}
