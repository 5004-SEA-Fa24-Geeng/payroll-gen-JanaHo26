package student;


public class TimeCard implements ITimeCard {
    private double hoursWorked;
    private String employeeID;

    /**
     * Create new timecard
     * @param hoursWorked the number of hours worked
     * @param employeeID the employee ID
     */
    public TimeCard(double hoursWorked, String employeeID) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative");
        }
        if (employeeID == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }
        this.hoursWorked = hoursWorked;
        this.employeeID = employeeID;
    }
    @Override
    public double getHoursWorked() {
        return hoursWorked;
    }

    @Override
    public String getEmployeeID() {
        return employeeID;
    }
}
