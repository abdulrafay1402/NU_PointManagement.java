package DataManagement;

import Exceptions.NotFoundException;
import Models.User;

public class UserManager extends BaseDataManager<User> {
    public UserManager() {
        super("users_data.ser");
    }

    public void addUser(User user) {
        items.add(user);
        saveData();
        printOperationResult("Add User", user);
    }

    public User getUser(String userId) throws NotFoundException {
        User user = items.findById(userId);
        printOperationResult("Get User", user);
        return user;
    }

    public void updateUser(User user) throws NotFoundException {
        User existing = items.findById(user.getId());
        saveData();
        printOperationResult("Update User", user);
    }

    public void DeleteUser(String userId) throws NotFoundException {
        User user = items.findById(userId);
        items.remove(user);
        saveData();
        printOperationResult("Delete User", user);
    }
}