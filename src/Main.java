
import DataManagement.TransportManager;
import Exceptions.NotFoundException;
import Exceptions.ValidationException;
import Models.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
public class Main {
    private static TransportManager tm = new TransportManager();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        displayTeamInfo();
        initializeSystem();
        displayMainMenu();
    }
    private static void displayTeamInfo() {
        System.out.println("=== Transport Management System ===");
        System.out.println("Team Members:");
        System.out.println("1. Bisma Shahid (24K-3012)");
        System.out.println("2. Afshal Liaquat (24K-2558)");
        System.out.println("3. Abdul Rafay (24K-3007)");
        System.out.println("===================================");
    }

    private static void initializeSystem() {
        tm.loadData("transport_data.ser");

        if (tm.getUsers().size() == 0) {
            System.out.println("\nNo existing data found. Creating sample data...");
            createSampleData();
        }
        tm.printFullStatus();
    }

    private static void displayMainMenu() {
        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. User Management");
            System.out.println("2. Driver Management");
            System.out.println("3. Route Management");
            System.out.println("4. Vehicle Management");
            System.out.println("5. Booking System");
            System.out.println("6. System Status");
            System.out.println("7. Save & Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> userManagement();
                case 2 -> driverManagement();
                case 3 -> routeManagement();
                case 4 -> vehicleManagement();
                case 5 -> bookingSystem();
                case 6 -> tm.printFullStatus();
                case 7 -> {
                    tm.saveData("transport_data.ser");
                    System.out.println("Data saved successfully. Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void vehicleManagement() {
        while (true) {
            System.out.println("\n===== VEHICLE MANAGEMENT =====");
            System.out.println("1. Add New Vehicle");
            System.out.println("2. View All Vehicles");
            System.out.println("3. View Vehicle Details");
            System.out.println("4. Update Vehicle");
            System.out.println("5. Delete Vehicle");
            System.out.println("6. Assign Driver to Vehicle");
            System.out.println("7. View Available Seats");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addVehicle();
                case 2 -> viewAllVehicles();
                case 3 -> viewVehicleDetails();
                case 4 -> updateVehicle();
                case 5 -> deleteVehicle();
                case 6 -> assignDriver();
                case 7 -> viewAvailableSeats();
                case 8 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    private static void addVehicle() {
        System.out.print("Enter Vehicle ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Registration Number: ");
        String regNum = scanner.nextLine();
        System.out.print("Is AC Vehicle? (true/false): ");
        boolean isAC = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Route ID: ");
        String routeId = scanner.nextLine();
        System.out.print("Enter Transporter Name: ");
        String transporter = scanner.nextLine();

        System.out.print("Vehicle Type (1-Bus, 2-Coaster): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Route route = tm.getRouteManager().getRoute(routeId);
            Vehicle vehicle;

            if (type == 1) {
                vehicle = new Bus(id, regNum, isAC, route, transporter);
            } else if (type == 2) {
                vehicle = new Coaster(id, regNum, isAC, route, transporter);
            } else {
                System.out.println("Invalid vehicle type!");
                return;
            }

            tm.getVehicleManager().addVehicle(vehicle);
            System.out.println("Vehicle added successfully!");
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllVehicles() {
        System.out.println("\n=== ALL VEHICLES ===");

        List<Vehicle> vehicles = (List<Vehicle>) tm.getVehicleManager().getAllItems();

        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
            System.out.println("-------------------");
        }
    }


    private static void viewVehicleDetails() {
        System.out.print("Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine();
        try {
            Vehicle vehicle = tm.getVehicleManager().getVehicle(vehicleId);
            System.out.println("\n=== VEHICLE DETAILS ===");
            System.out.println("ID: " + vehicle.getId());
            System.out.println("Registration: " + vehicle.getRegistrationNumber());
            System.out.println("Type: " + vehicle.getClass().getSimpleName());
            System.out.println("AC: " + (vehicle.isAC() ? "Yes" : "No"));
            System.out.println("Route: " + vehicle.getRoute());
            System.out.println("Transporter: " + vehicle.getTransporter());
            System.out.println("Driver: " +
                    (vehicle.getDriver() != null ? vehicle.getDriver().getName() : "Not assigned"));
            System.out.println("Total Seats: " + vehicle.getSeats().size());
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateVehicle() {
        System.out.print("Enter Vehicle ID to update: ");
        String vehicleId = scanner.nextLine();
        try {
            Vehicle vehicle = tm.getVehicleManager().getVehicle(vehicleId);
            System.out.println("Current details: " + vehicle);

            System.out.print("Enter new registration number (leave blank to keep current): ");
            String regNum = scanner.nextLine();
            if (!regNum.isEmpty()) vehicle.setRegistrationNumber(regNum);

            System.out.print("Update AC status? (true/false, leave blank to skip): ");
            String acInput = scanner.nextLine();
            if (!acInput.isEmpty()) vehicle.setAC(Boolean.parseBoolean(acInput));

            System.out.print("Enter new transporter (leave blank to keep current): ");
            String transporter = scanner.nextLine();
            if (!transporter.isEmpty()) vehicle.setTransporter(transporter);

            tm.getVehicleManager().updateVehicle(vehicle);
            System.out.println("Vehicle updated successfully!");
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteVehicle() {
        System.out.print("Enter Vehicle ID to delete: ");
        String vehicleId = scanner.nextLine();
        try {
            tm.getVehicleManager().deleteVehicle(vehicleId);
            System.out.println("Vehicle deleted successfully!");
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void assignDriver() {
        System.out.print("Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine();
        System.out.print("Enter Driver ID: ");
        String driverId = scanner.nextLine();

        try {
            Vehicle vehicle = tm.getVehicleManager().getVehicle(vehicleId);
            Driver driver = tm.getDriverManager().getDriver(driverId);

            vehicle.assignDriver(driver);
            tm.getVehicleManager().updateVehicle(vehicle);
            System.out.println("Driver assigned successfully!");
        } catch (NotFoundException | ValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAvailableSeats() {
        System.out.print("Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine();
        System.out.print("User Type (1-Student, 2-Faculty): ");
        int userType = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Vehicle vehicle = tm.getVehicleManager().getVehicle(vehicleId);
            Class<?> type = userType == 1 ? Student.class : Faculty.class;

            List<Seat> availableSeats = vehicle.getAvailableSeats(type);
            System.out.println("\n=== AVAILABLE SEATS ===");
            System.out.println("Total available: " + availableSeats.size());
            availableSeats.forEach(seat -> System.out.println(seat.getNumber()));
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void routeManagement() {
        while (true) {
            System.out.println("\n===== ROUTE MANAGEMENT =====");
            System.out.println("1. Add New Route");
            System.out.println("2. View All Routes");
            System.out.println("3. View Route Details");
            System.out.println("4. Update Route");
            System.out.println("5. Delete Route");
            System.out.println("6. Find Routes by Distance");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addRoute();
                case 2 -> viewAllRoutes();
                case 3 -> viewRouteDetails();
                case 4 -> updateRoute();
                case 5 -> deleteRoute();
                case 6 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    private static void addRoute() {
        System.out.print("Enter Route ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Start Location: ");
        String start = scanner.nextLine();
        System.out.print("Enter End Location: ");
        String end = scanner.nextLine();
        System.out.print("Enter Distance (km): ");
        double distance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Route newRoute = new Route(id, start, end, distance);
        tm.getRouteManager().addRoute(newRoute);
        System.out.println("Route added successfully!");
    }

    private static void viewAllRoutes() {
        System.out.println("\n=== ALL ROUTES ===");

        List<Route> routes = (List<Route>) tm.getRouteManager().getAllItems();

        if (routes == null || routes.isEmpty()) {
            System.out.println("No routes found.");
            return;
        }

        for (Route route : routes) {
            System.out.println(route);
            System.out.println("-------------------");
        }
    }


    private static void viewRouteDetails() {
        System.out.print("Enter Route ID: ");
        String routeId = scanner.nextLine();
        try {
            Route route = tm.getRouteManager().getRoute(routeId);
            System.out.println("\n=== ROUTE DETAILS ===");
            System.out.println("ID: " + route.getId());
            System.out.println("From: " + route.getStartLocation());
            System.out.println("To: " + route.getEndLocation());
            System.out.println("Distance: " + route.getDistance() + "km");
            System.out.println("Type: " + (route.isLongDistance() ? "Long" : "Short") + " distance");
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateRoute() {
        System.out.print("Enter Route ID to update: ");
        String routeId = scanner.nextLine();
        try {
            Route route = tm.getRouteManager().getRoute(routeId);
            System.out.println("Current details: " + route);

            System.out.print("Enter new start location (leave blank to keep current): ");
            String start = scanner.nextLine();
            if (!start.isEmpty()) route.setStartLocation(start);

            System.out.print("Enter new end location (leave blank to keep current): ");
            String end = scanner.nextLine();
            if (!end.isEmpty()) route.setEndLocation(end);

            System.out.print("Enter new distance (enter 0 to keep current): ");
            double distance = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (distance > 0) {
                route.setDistance(distance);
                route.setLongDistance(distance > Route.LONG_DISTANCE_THRESHOLD);
            }

            tm.getRouteManager().updateRoute(route);
            System.out.println("Route updated successfully!");
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteRoute() {
        System.out.print("Enter Route ID to delete: ");
        String routeId = scanner.nextLine();
        try {
            tm.getRouteManager().deleteRoute(routeId);
            System.out.println("Route deleted successfully!");
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void driverManagement() {
        while (true) {
            System.out.println("\n===== DRIVER MANAGEMENT =====");
            System.out.println("1. Add New Driver");
            System.out.println("2. View All Drivers");
            System.out.println("3. View Driver Details");
            System.out.println("4. Update Driver");
            System.out.println("5. Delete Driver");
            System.out.println("6. Assign Vehicle to Driver");
            System.out.println("7. Remove Vehicle Assignment");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addDriver();
                case 2 -> viewAllDrivers();
                case 3 -> viewDriverDetails();
                case 4 -> updateDriver();
                case 5 -> deleteDriver();
                case 6 -> assignVehicleToDriver();
                case 7 -> removeVehicleAssignment();
                case 8 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    private static void addDriver() {
        System.out.print("Enter Driver ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Driver Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter License Number: ");
        String license = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();

        Driver driver = new Driver(id, name, license, contact);
        tm.getDriverManager().addDriver(driver);
        System.out.println("Driver added successfully!");
    }

    private static void viewAllDrivers() {
        System.out.println("\n=== ALL DRIVERS ===");

        List<Driver> drivers = (List<Driver>) tm.getDriverManager().getAllItems();

        if (drivers == null || drivers.isEmpty()) {
            System.out.println("No drivers found.");
            return;
        }

        for (Driver driver : drivers) {
            System.out.println(driver);
            Vehicle assignedVehicle = driver.getAssignedVehicle();
            System.out.println("Assigned Vehicle: " + (assignedVehicle != null ? assignedVehicle.getId() : "None"));
            System.out.println("-------------------");
        }
    }


    private static void viewDriverDetails() {
        System.out.print("Enter Driver ID: ");
        String driverId = scanner.nextLine();
        try {
            Driver driver = tm.getDriverManager().getDriver(driverId);
            System.out.println("\n=== DRIVER DETAILS ===");
            System.out.println("ID: " + driver.getId());
            System.out.println("Name: " + driver.getName());
            System.out.println("License: " + driver.getLicenseNumber());
            System.out.println("Contact: " + driver.getContact());
            System.out.println("Assigned Vehicle: " +
                    (driver.getAssignedVehicle() != null ?
                            driver.getAssignedVehicle().getId() : "None"));
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateDriver() {
        System.out.print("Enter Driver ID to update: ");
        String driverId = scanner.nextLine();
        try {
            Driver driver = tm.getDriverManager().getDriver(driverId);
            System.out.println("Current details: " + driver);

            System.out.print("Enter new name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) driver.setName(name);

            System.out.print("Enter new license number (leave blank to keep current): ");
            String license = scanner.nextLine();
            if (!license.isEmpty()) driver.setLicenseNumber(license);

            System.out.print("Enter new contact number (leave blank to keep current): ");
            String contact = scanner.nextLine();
            if (!contact.isEmpty()) driver.setContact(contact);

            tm.getDriverManager().updateDriver(driver);
            System.out.println("Driver updated successfully!");
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteDriver() {
        System.out.print("Enter Driver ID to delete: ");
        String driverId = scanner.nextLine();
        try {
            Driver driver = tm.getDriverManager().getDriver(driverId);
            if (driver.getAssignedVehicle() != null) {
                System.out.println("Warning: Driver is currently assigned to a vehicle!");
                System.out.print("Are you sure you want to delete? (yes/no): ");
                String confirm = scanner.nextLine();
                if (!confirm.equalsIgnoreCase("yes")) {
                    return;
                }
            }
            tm.getDriverManager().deleteDriver(driverId);
            System.out.println("Driver deleted successfully!");
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void assignVehicleToDriver() {
        System.out.print("Enter Driver ID: ");
        String driverId = scanner.nextLine();
        System.out.print("Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine();

        try {
            Driver driver = tm.getDriverManager().getDriver(driverId);
            Vehicle vehicle = tm.getVehicleManager().getVehicle(vehicleId);

            driver.assignVehicle(vehicle);
            vehicle.setDriver(driver);

            tm.getDriverManager().updateDriver(driver);
            tm.getVehicleManager().updateVehicle(vehicle);

            System.out.println("Vehicle assigned successfully!");
        } catch (NotFoundException | ValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeVehicleAssignment() {
        System.out.print("Enter Driver ID: ");
        String driverId = scanner.nextLine();

        try {
            Driver driver = tm.getDriverManager().getDriver(driverId);
            if (driver.getAssignedVehicle() == null) {
                System.out.println("Driver has no vehicle assignment!");
                return;
            }

            Vehicle vehicle = driver.getAssignedVehicle();
            driver.removeAssignment();
            vehicle.removeDriver();

            tm.getDriverManager().updateDriver(driver);
            tm.getVehicleManager().updateVehicle(vehicle);

            System.out.println("Vehicle assignment removed successfully!");
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void userManagement() {
        while (true) {
            System.out.println("\n===== USER MANAGEMENT =====");
            System.out.println("1. Register Student");
            System.out.println("2. Register Faculty");
            System.out.println("3. View All Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Search User by ID");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> registerStudent();
                case 2 -> registerFaculty();
                case 3 -> tm.printAllUsers();
                case 4 -> updateUser();
                case 5 -> deleteUser();
                case 6 -> searchUser();
                case 7 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }


    private static void registerStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Roll Number: ");
        String rollNumber = scanner.nextLine();
        tm.registerUser(new Student(id,name,email,phone,rollNumber));
        System.out.println("Student registered successfully!");
    }

    private static void registerFaculty() {
        System.out.print("Enter Faculty ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Employee ID: ");
        String empId = scanner.nextLine();

        tm.registerUser(new Faculty(id, name, email, phone, empId));
        System.out.println("Faculty registered successfully!");
    }

    private static void updateUser() {
        System.out.print("Enter User ID to update: ");
        String userId = scanner.nextLine();

        try {
            User user = tm.getUserManager().getUser(userId);
            System.out.println("Current details: " + user);

            System.out.print("Enter new name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) user.setName(name);

            System.out.print("Enter new email (leave blank to keep current): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) user.setEmail(email);

            System.out.print("Enter new phone (leave blank to keep current): ");
            String phone = scanner.nextLine();
            if (!phone.isEmpty()) user.setPhone(phone);

            // Update specific fields based on user type
            if (user instanceof Student) {
                System.out.print("Enter new roll number (leave blank to keep current): ");
                String roll = scanner.nextLine();
                if (!roll.isEmpty()) ((Student)user).setRollNumber(roll);
            } else if (user instanceof Faculty) {
                System.out.print("Enter new employee ID (leave blank to keep current): ");
                String empId = scanner.nextLine();
                if (!empId.isEmpty()) ((Faculty)user).setEmployeeId(empId);
            }

            tm.getUserManager().updateUser(user);
            System.out.println("User updated successfully!");
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        String userId = scanner.nextLine();

        try {
            tm.getUserManager().DeleteUser(userId);
            System.out.println("User deleted successfully!");
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchUser() {
        System.out.print("Enter User ID to search: ");
        String userId = scanner.nextLine();

        try {
            User user = tm.getUserManager().getUser(userId);
            System.out.println("User found:");
            System.out.println(user);
            if (user instanceof Student) {
                System.out.println("Type: Student, Roll: " + ((Student)user).getRollNumber());
            } else if (user instanceof Faculty) {
                System.out.println("Type: Faculty, Employee ID: " + ((Faculty)user).getEmployeeId());
            }
            System.out.println("Payment Status: " + (user.getPaymentStatus() ? "Paid" : "Pending"));
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void bookingSystem() {
        while (true) {
            System.out.println("\n===== BOOKING SYSTEM =====");
            System.out.println("1. Make Booking");
            System.out.println("2. View All Bookings");
            System.out.println("3. View Booking Details");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> makeBooking();
                case 2 -> tm.printAllBookings();
                case 3 -> viewBookingDetails();
                case 4 -> cancelBooking();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void makeBooking() {
        try {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter Vehicle ID: ");
            String vehicleId = scanner.nextLine();
            System.out.print("Enter Seat Number: ");
            String seatNumber = scanner.nextLine();

            User user = tm.getUsers().findById(userId);
            if (!user.getPaymentStatus()) {
                System.out.println("User hasn't made payment. Processing payment...");
                user.makePayment();
            }

            Booking booking = tm.makeBooking(userId, vehicleId, seatNumber);
            System.out.println("Booking successful: " + booking);
        } catch (NotFoundException | ValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void viewBookingDetails() {
        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();
        try {
            Booking booking = tm.getBookingManager().getBookings(bookingId);
            System.out.println("\n=== Booking Details ===");
            System.out.println("ID: " + booking.getId());
            System.out.println("User: " + booking.getUser().getName());
            System.out.println("Vehicle: " + booking.getVehicle().getId());
            System.out.println("Seat: " + booking.getSeat());
            System.out.println("Date: " + booking.getBookingDate());
            System.out.println("Fare: $" + booking.getFare());
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void cancelBooking() {
        System.out.print("Enter Booking ID to cancel: ");
        String bookingId = scanner.nextLine();
        try {
            tm.getBookingManager().deleteBooking(bookingId);
            System.out.println("Booking cancelled successfully!");
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void createSampleData() {
        // Users
        Student student = new Student("S001", "Ali Khan", "ali@example.com", "03001234567", "FA20-BCS-001");
        Faculty professor = new Faculty("F001", "Dr. Ahmed", "ahmed@example.com", "03007654321", "EMP-1001");
        tm.registerUser(student);
        tm.registerUser(professor);

        Driver driver1 = new Driver("D001", "Nadeem Ahmed", "LIC-12345", "03001112222");
        Driver driver2 = new Driver("D002", "Zulfiqar Ali", "LIC-54321", "03003334444");
        tm.addDriver(driver1);
        tm.addDriver(driver2);


        Route shortRoute = new Route("R001", "Gulberg", "FAST-NUCES", 26.1);
        Route longRoute = new Route("R002", "DHA", "FAST-NUCES", 24.3);
        tm.addRoute(shortRoute);
        tm.addRoute(longRoute);

        Bus regularBus = new Bus("V001", "ABC-123", false, shortRoute, "Nadeem Transporter");
        Coaster acCoaster = new Coaster("V002", "XYZ-789", true, longRoute, "Zulfiqar Transporter");
        tm.addVehicle(regularBus);
        tm.addVehicle(acCoaster);

        try {
            tm.assignDriverToVehicle(driver1,regularBus);
            tm.assignDriverToVehicle(driver2,acCoaster);
        } catch (Exception e) {
            System.out.println("Error assigning drivers: " + e.getMessage());
        }
    }
}