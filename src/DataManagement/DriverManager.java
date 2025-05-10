package DataManagement;

import Exceptions.NotFoundException;
import Models.Driver;

public class DriverManager extends BaseDataManager<Driver> {
    public DriverManager() {
        super("drivers_data.ser");
    }

    public void addDriver(Driver driver) {
        items.add(driver);
        saveData();
        printOperationResult("Add Driver", driver);
    }

    public Driver getDriver(String driverId) throws NotFoundException {
        Driver driver = items.findById(driverId);
        printOperationResult("Get Driver", driver);
        return driver;
    }

    public void updateDriver(Driver driver) throws NotFoundException {
        Driver existing = items.findById(driver.getId());
        saveData();
        printOperationResult("Update Driver", driver);
    }

    public void deleteDriver(String driverId) throws NotFoundException {
        Driver driver = items.findById(driverId);
        items.remove(driver);
        saveData();
        printOperationResult("Delete Driver", driver);
    }
}