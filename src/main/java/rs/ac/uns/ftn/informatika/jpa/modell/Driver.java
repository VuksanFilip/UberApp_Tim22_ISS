package rs.ac.uns.ftn.informatika.jpa.modell;

import java.util.ArrayList;

public class Driver extends User{

    ArrayList<Document> documents;
    ArrayList<Ride> rides;
    Vehicle vehicle;

    public Driver(String firstName, String lastName, String picture, String phoneNumber, String email, String address, String password, boolean blocked, boolean active) {
        super(firstName, lastName, picture, phoneNumber, email, address, password, blocked, active);
    }

    public Driver(String firstName, String lastName, String picture, String phoneNumber, String email, String address, String password, boolean blocked, boolean active, ArrayList<Document> documents, ArrayList<Ride> rides, Vehicle vehicle) {
        super(firstName, lastName, picture, phoneNumber, email, address, password, blocked, active);
        this.documents = documents;
        this.rides = rides;
        this.vehicle = vehicle;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }

    public ArrayList<Ride> getRides() {
        return rides;
    }

    public void setRides(ArrayList<Ride> rides) {
        this.rides = rides;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
