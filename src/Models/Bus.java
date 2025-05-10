package Models;

public class Bus extends Vehicle {
    public Bus(String id, String registrationNumber, boolean isAC, Route route, String transporter) {
        super(id, registrationNumber, isAC, route, transporter);
    }

    @Override
    protected void initializeSeats() {
        // First 40 seats for students, last 12 for faculty
        for (int i = 1; i <= 40; i++) {
            seats.add(new Seat("S" + i, "student"));
        }
        for (int i = 1; i <= 12; i++) {
            seats.add(new Seat("F" + i, "faculty"));
        }
    }
}