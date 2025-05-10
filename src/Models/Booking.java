package Models;
import java.time.LocalDate;

public class Booking {
    private String id;
    private User user;
    private Vehicle vehicle;
    private Seat seat;
    private LocalDate bookingDate;
    private double fare;

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public Booking(String id, User user, Vehicle vehicle, Seat seat, LocalDate bookingDate) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.seat = seat;
        this.bookingDate = bookingDate;
        this.fare = user.calculateFare(vehicle.isAC());
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    @Override
    public String toString() {
        return "Booking " + id + ": " + user.getName() + " booked " + seat + " on " +
                vehicle + " on " + bookingDate.toString();
    }
}