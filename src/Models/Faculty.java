package Models;
public class Faculty extends User {
    private String employeeId;
    private double baseFare = 2500;

    public Faculty(String id, String name, String email, String phone, String employeeId) {
        super(id, name, email, phone);
        this.employeeId = employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public String getEmployeeId() {
        return employeeId;
    }
}