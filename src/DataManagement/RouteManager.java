package DataManagement;

import Exceptions.NotFoundException;
import Models.Route;

import java.util.Arrays;

public class RouteManager extends BaseDataManager<Route> {
    public RouteManager() {
        super("routes_data.ser");
    }

    public void addRoute(Route route) {
        items.add(route);
        saveData();
        printOperationResult("Add Route", route);
    }

    public Route getRoute(String routeId) throws NotFoundException {
        Route route = items.findById(routeId);
        printOperationResult("Get Route", route);
        return route;
    }

    public void updateRoute(Route route) throws NotFoundException {
        Route existing = items.findById(route.getId());
        saveData();
        printOperationResult("Update Route", route);
    }

    public void deleteRoute(String routeId) throws NotFoundException {
        Route route = items.findById(routeId);
        items.remove(route);
        saveData();
        printOperationResult("Delete Route", route);
    }
}