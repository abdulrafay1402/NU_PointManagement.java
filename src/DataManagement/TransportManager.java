package DataManagement;
import Exceptions.NotFoundException;
import Exceptions.ValidationException;
import Models.*;

import java.io.*;
import java.time.LocalDate;


public class TransportManager {
    private final UserManager userManager = new UserManager();
    private final VehicleManager vehicleManager = new VehicleManager();
    private final DriverManager driverManager = new DriverManager();
    private final RouteManager routeManager = new RouteManager();
    private final BookingManager bookingManager = new BookingManager();
    private GenericList<Driver> drivers;
    private GenericList<User> users;
    private GenericList<Route> routes;
    private GenericList<Vehicle> vehicles;
    private GenericList<Booking> bookings;

    public GenericList<User> getUsers() {
        return users;
    }

    // User operations
    public void registerUser(User user) {
        userManager.addUser(user);
    }
    public void saveData(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this.users);
            oos.writeObject(this.drivers);
            oos.writeObject(this.routes);
            oos.writeObject(this.vehicles);
            oos.writeObject(this.bookings);
            System.out.println("Data saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void loadData(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            this.users = (GenericList<User>) ois.readObject();
            this.drivers = (GenericList<Driver>) ois.readObject();
            this.routes = (GenericList<Route>) ois.readObject();
            this.vehicles = (GenericList<Vehicle>) ois.readObject();
            this.bookings = (GenericList<Booking>) ois.readObject();
            System.out.println("Data loaded successfully from " + filename);
        } catch (FileNotFoundException e) {
            System.err.println("Data file not found, starting with empty data");
            // Initialize empty lists if file doesn't exist
            this.users = new GenericList<>();
            this.drivers = new GenericList<>();
            this.routes = new GenericList<>();
            this.vehicles = new GenericList<>();
            this.bookings = new GenericList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Vehicle operations
    public void addVehicle(Vehicle vehicle) {
        vehicleManager.addVehicle(vehicle);
    }
    public void addRoute(Route r){
        routes.add(r);
    }
    public void addDriver(Driver d){
        drivers.add(d);
    }

    // Booking operations
    public Booking makeBooking(String userId, String vehicleId, String seatNumber)
            throws NotFoundException, ValidationException {
        User user = userManager.getUser(userId);
        Vehicle vehicle = vehicleManager.getVehicle(vehicleId);

        if (hasMonthlyBooking(userId)) {
            throw new ValidationException("Monthly booking limit reached");
        }

        vehicle.bookSeat(seatNumber, user);
        Booking booking = new Booking(
                generateBookingId(),
                user,
                vehicle,
                vehicle.getSeatByNumber(seatNumber),
                LocalDate.now()
        );
        bookingManager.addBooking(booking);
        return booking;
    }
    public void assignDriverToVehicle(Driver d,Vehicle v)throws NotFoundException {
        if(drivers.getAll().contains(d)&& vehicles.getAll().contains(v)){
            if(d.getAssignedVehicle()==null&&v.getDriver()==null){
                v.setDriver(d);
                d.assignVehicle(v);
            }
        }else throw new NotFoundException("Driver or Vehicle must be in list of yours fisrt");
    }
    public void printAllBookings(){
        try{
           bookingManager.loadData();
        }catch (NotFoundException e){
            System.out.println("NO bookings Found");
        }
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public VehicleManager getVehicleManager() {
        return vehicleManager;
    }

    public DriverManager getDriverManager() {
        return driverManager;
    }

    public RouteManager getRouteManager() {
        return routeManager;
    }

    public BookingManager getBookingManager() {
        return bookingManager;
    }

    public void printAllUsers(){
        try{
            userManager.loadData();
        }catch (NotFoundException e){
            System.out.println("No user Found");
        }
    }
    private boolean hasMonthlyBooking(String userId) {
        for (Booking b : bookingManager.getBooking()) {
            if (b.getUser().getId().equals(userId) &&
                    b.getBookingDate().getMonthValue() == LocalDate.now().getMonthValue()) {
                return true;
            }
        }
        return false;
    }
    private String generateBookingId() {
        return "BKG-" + System.currentTimeMillis();
    }

    public void printFullStatus() {
    }
    public static TransportManager initialize() {
        TransportManager tm = new TransportManager();
        tm.printFullStatus();
        return tm;
    }

}