package Models;

import Exceptions.ValidationException;

public class Driver {
    private String id;
    private String name;
    private String licenseNumber;
    private String contact;
    private Vehicle assignedVehicle;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Driver(String id, String name, String licenseNumber, String contact) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.contact = contact;
    }

    public void assignVehicle(Vehicle vehicle) throws ValidationException {
        if (assignedVehicle != null) {
            throw new ValidationException("Driver is already assigned to a vehicle");
        }
        this.assignedVehicle = vehicle;
    }

    public void removeAssignment() {
        this.assignedVehicle = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Vehicle getAssignedVehicle() {
        return assignedVehicle;
    }

    @Override
    public String toString() {
        return "Driver: " + name + " (License: " + licenseNumber + ")";
    }
}
