package DataManagement;
import Models.GenericList;

import java.io.*;
import java.util.List;

abstract class BaseDataManager<T> {
    protected GenericList<T> items = new GenericList<>();
    protected final String dataFileName;

    public GenericList<T> getAllItems() {
        return items;
    }

    public BaseDataManager(String dataFileName) {
        this.dataFileName = dataFileName;
        loadData();
    }

    protected void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFileName))) {
            oos.writeObject(items.getAll());
        } catch (IOException e) {
            System.out.println("Error saving " + dataFileName + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    protected void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFileName))) {
            List<T> loadedItems = (List<T>) ois.readObject();
            items = new GenericList<>();
            loadedItems.forEach(items::add);
        } catch (FileNotFoundException e) {
            System.out.println("No existing " + dataFileName + " found. Starting fresh.");
        } catch (Exception e) {
            System.out.println("Error loading " + dataFileName + ": " + e.getMessage());
        }
    }

    public void printAll() {
        System.out.println("\n=== " + getClass().getSimpleName() + " Data ===");
        items.getAll().forEach(System.out::println);
    }

    protected void printOperationResult(String operation, T item) {
        System.out.println("\nOperation: " + operation);
        System.out.println("Affected: " + item);
        printAll();
    }

}