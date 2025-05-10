package Models;
public class Student extends User {
    private String rollNumber;
    private double baseFare = 1500;

    public Student(String id, String name, String email, String phone, String rollNumber) {
        super(id, name, email, phone);
        this.rollNumber = rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    @Override
    public double calculateFare(boolean isAC) {
        return baseFare + (isAC ? 2000 : 0);
    }

    public String getRollNumber() {
        return rollNumber;
    }
}