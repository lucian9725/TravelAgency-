package sda.academy.travelagency.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column
    private String continent;

    // Relație bidirecțională cu tururile
    @OneToMany(mappedBy = "whereTo", cascade = CascadeType.ALL)
    private List<Tour> tours;

    // Relație cu hotelurile
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Hotel> hotels;

}
