
package Models;

public class Coaster extends Vehicle {
    public Coaster(String id, String registrationNumber, boolean isAC, Route route, String transporter) {
        super(id, registrationNumber, isAC, route, transporter);
    }

    @Override
    protected void initializeSeats() {
        // First 25 seats for students, last 7 for faculty
        for (int i = 1; i <= 25; i++) {
            seats.add(new Seat("S" + i, "student"));
        }
        for (int i = 1; i <= 7; i++) {
            seats.add(new Seat("F" + i, "faculty"));
        }
    }
}