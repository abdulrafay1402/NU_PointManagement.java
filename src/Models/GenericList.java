package Models;
import Exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class GenericList<T> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    public T findById(String id) throws NotFoundException {
        for (T item : items) {
            try {
                String itemId = (String) item.getClass().getMethod("getId").invoke(item);
                if (itemId.equals(id)) {
                    return item;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new NotFoundException("Item with ID " + id + " not found");
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public int size() {
        return items.size();
    }


}
