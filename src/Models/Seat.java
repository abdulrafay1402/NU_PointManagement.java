package Models;

import Exceptions.ValidationException;

public class Seat {
    private String number;
    private String type; // 'student' or 'faculty'
    private boolean isBooked;
    private String bookedBy;

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public Seat(String number, String type) {
        this.number = number;
        this.type = type;
        this.isBooked = false;
        this.bookedBy = null;
    }

    public void book(User user) throws ValidationException {
        if (isBooked) {
            throw new ValidationException("Seat is already booked");
        }

        // Check if seat type matches user type
        if ((user instanceof Student) && !type.equals("student")) {
            throw new ValidationException("Students can only book student seats");
        }
        if ((user instanceof Faculty) && !type.equals("faculty")) {
            throw new ValidationException("Faculty can only book faculty seats");
        }

        isBooked = true;
        bookedBy = user.getId();
    }

    public void release() {
        isBooked = false;
        bookedBy = null;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public boolean isBooked() {
        return isBooked;
    }

    @Override
    public String toString() {
        String status = isBooked ? "Booked" : "Available";
        return "Seat " + number + " (" + type + "): " + status;
    }
}