package Models;
public abstract class User {
    protected String id;
    protected String name;
    protected String email;
    protected String phone;
    protected boolean paymentStatus;

    public User(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.paymentStatus = false;
    }

    public abstract double calculateFare(boolean isAC);

    public void makePayment() {
        this.paymentStatus = true;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getPaymentStatus() {
        return paymentStatus;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + name + " (ID: " + id + ")";
    }
}
