package Models;
public class Route {
    public static final double LONG_DISTANCE_THRESHOLD = 30; // km

    private String id;
    private String startLocation;
    private String endLocation;

    public void setId(String id) {
        this.id = id;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setLongDistance(boolean longDistance) {
        isLongDistance = longDistance;
    }

    private double distance;
    private boolean isLongDistance;

    public Route(String id, String startLocation, String endLocation, double distance) {
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.distance = distance;
        this.isLongDistance = distance > LONG_DISTANCE_THRESHOLD;
    }

    public String getId() {
        return id;
    }

    public boolean isLongDistance() {
        return isLongDistance;
    }

    @Override
    public String toString() {
        return "Route " + id + ": " + startLocation + " to " + endLocation +
                " (" + distance + "km, " + (isLongDistance ? "Long" : "Short") + " distance)";
    }
}