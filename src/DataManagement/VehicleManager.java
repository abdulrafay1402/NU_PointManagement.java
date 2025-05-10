package DataManagement;

import Exceptions.NotFoundException;
import Models.Vehicle;

public class VehicleManager extends BaseDataManager<Vehicle> {
    public VehicleManager() {
        super("vehicles_data.ser");
    }

    public void addVehicle(Vehicle vehicle) {
        items.add(vehicle);
        saveData();
        printOperationResult("Add Vehicle", vehicle);
    }

    public Vehicle getVehicle(String vehicleId) throws NotFoundException {
        Vehicle vehicle = items.findById(vehicleId);
        printOperationResult("Get Vehicle", vehicle);
        return vehicle;
    }

    public void updateVehicle(Vehicle vehicle) throws NotFoundException {
        Vehicle existing = items.findById(vehicle.getId());
        saveData();
        printOperationResult("Update Vehicle", vehicle);
    }

    public void deleteVehicle(String vehicleId) throws NotFoundException {
        Vehicle vehicle = items.findById(vehicleId);
        items.remove(vehicle);
        saveData();
        printOperationResult("Delete Vehicle", vehicle);
    }

}