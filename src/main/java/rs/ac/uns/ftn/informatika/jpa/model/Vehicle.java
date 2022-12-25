package rs.ac.uns.ftn.informatika.jpa.model;

import rs.ac.uns.ftn.informatika.jpa.dto.response.DestinationResponseDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.response.DriverDocumentResponseDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.response.DriverVehicleResponseDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.response.LocationResponseDTO;

import java.util.ArrayList;

public class Vehicle {
    Long id;
    Driver driver;
    String vehicleModel;
    VehicleType type;
    String registarskeTablice;
    int seats;
    Location location;
    boolean babyFriendly;
    boolean petFriendly;
    ArrayList<Review> reviews;

    public Vehicle(Long id) {
        this.id = id;
    }


    public Vehicle() {
    }



    public Vehicle(Long id, Long driverId, VehicleType type, String vehicleModel, String registarskeTablice, Location currentLocation, int seats, boolean babyFriendly, boolean petFriendly) {
        this.id = id;
        this.driver = new Driver(driverId);
        this.vehicleModel = vehicleModel;
        this.type = type;
        this.registarskeTablice = registarskeTablice;
        this.seats = seats;
        this.location = currentLocation;
        this.babyFriendly = babyFriendly;
        this.petFriendly = petFriendly;
    }

    public Vehicle(Long id, Driver driver, String vehicleModel, VehicleType type, String registarskeTablice, int seats, Location location, boolean babyFriendly, boolean petFriendly, ArrayList<Review> reviews) {
        this.id = id;
        this.driver = driver;
        this.vehicleModel = vehicleModel;
        this.type = type;
        this.registarskeTablice = registarskeTablice;
        this.seats = seats;
        this.location = location;
        this.babyFriendly = babyFriendly;
        this.petFriendly = petFriendly;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType tip) {
        this.type = tip;
    }

    public String getRegistarskeTablice() {
        return registarskeTablice;
    }

    public void setRegistarskeTablice(String registarskeTablice) {
        this.registarskeTablice = registarskeTablice;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isBabyFriendly() {
        return babyFriendly;
    }

    public void setBabyFriendly(boolean babyFriendly) {
        this.babyFriendly = babyFriendly;
    }

    public boolean isPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public DriverVehicleResponseDTO parseToResponse(){
        DestinationResponseDTO locationResponseDTO = new DestinationResponseDTO();

        locationResponseDTO=new DestinationResponseDTO(location.getAddress(),location.getLatitude(), location.getLongitude());

        return new DriverVehicleResponseDTO(this.getId(), this.getDriver().getId(), this.type.type, this.getVehicleModel(), this.getRegistarskeTablice(), locationResponseDTO, this.getSeats(), this.isBabyFriendly(), this.isPetFriendly());
    }

}
