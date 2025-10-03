package sda.academy.travelagency.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Location {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String continent;

    @OneToMany(mappedBy = "location")    // relatie bidirectionala cu Order,
                                        // o locatie poate sa se gaseasca in mai multe order
    private List<Order> orders;

    @OneToMany
    private List<Hotel> hotels;
}
