package rs.ac.uns.ftn.informatika.jpa.model;

import lombok.*;
import rs.ac.uns.ftn.informatika.jpa.dto.response.*;
import rs.ac.uns.ftn.informatika.jpa.model.enums.VehicleName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class FavoriteRoute {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String favoriteName;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "favourite_route_route", joinColumns = @JoinColumn(name = "favourite_route_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "route_id", referencedColumnName = "id"))
    private List<Route> route;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "favourite_route_passenger", joinColumns = @JoinColumn(name = "favourite_route_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id"))
    private List<Passenger> passengers;

    @Enumerated(EnumType.STRING)
    private VehicleName vehicleType;
    private boolean babyTransport;
    private boolean petTransport;

    public FavoriteRoute(String favoriteName, List<Route> route, List<Passenger> passengers, VehicleName vehicleType, boolean babyTransport, boolean petTransport) {
        this.favoriteName = favoriteName;
        this.route = route;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }



}
