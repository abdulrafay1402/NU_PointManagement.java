package Models;
import Exceptions.NotFoundException;
import Exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    protected String id;
    protected String registrationNumber;
    protected boolean isAC;
    protected Route route;
    protected String transporter;
    protected Driver driver;
    protected List<Seat> seats;

    public Vehicle(String id, String registrationNumber, boolean isAC, Route route, String transporter) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.isAC = isAC;
        this.route = route;
        this.transporter = transporter;
        this.seats = new ArrayList<>();
        initializeSeats();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setAC(boolean AC) {
        isAC = AC;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getTransporter() {
        return transporter;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    protected abstract void initializeSeats();

    public void assignDriver(Driver driver) throws ValidationException {
        if (this.driver != null) {
            throw new ValidationException("Vehicle already has a driver assigned");
        }
        this.driver = driver;
        driver.assignVehicle(this);
    }

    public void removeDriver() {
        if (driver != null) {
            driver.removeAssignment();
            driver = null;
        }
    }

    public List<Seat> getAvailableSeats(Class<?> userType) {
        String seatType = userType == Student.class ? "student" : "faculty";
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isBooked() && seat.getType().equals(seatType)) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    public void bookSeat(String seatNumber, User user) throws ValidationException, NotFoundException {
        if (!user.getPaymentStatus()) {
            throw new ValidationException("Payment not completed. Cannot book seat.");
        }

        for (Seat seat : seats) {
            if (seat.getNumber().equals(seatNumber)) {
                seat.book(user);
                return;
            }
        }

        throw new NotFoundException("Seat " + seatNumber + " not found in vehicle " + id);
    }

    public Seat getSeatByNumber(String seatNumber) throws NotFoundException {
        for (Seat seat : seats) {
            if (seat.getNumber().equals(seatNumber)) {
                return seat;
            }
        }
        throw new NotFoundException("Seat not found");
    }

    public String getId() {
        return id;
    }

    public boolean isAC() {
        return isAC;
    }

    public Route getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id + " (" + registrationNumber + "), " +
                "AC: " + (isAC ? "Yes" : "No") + ", " + route;
    }
}