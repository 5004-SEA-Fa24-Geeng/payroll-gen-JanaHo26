package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Abstract base class for all employee types
 */
public abstract class Employee implements IEmployee {
    private String name;
    private String id;
    private double payRate;
    private BigDecimal ytdEarnings;
    private BigDecimal ytdTaxesPaid;
    private double pretaxDeductions;
    private EmployeeType type;

    // Tax constants
    protected static final BigDecimal TAX_RATE = new BigDecimal("0.2265");
    protected static final int SCALE = 2;


    /**
     * create new employee
     *
     * @param name             the employee name
     * @param id               employee id
     * @param payRate          pay rate
     * @param ytdEarnings      year to date earnings
     * @param ytdTaxesPaid     year to date taxes paid
     * @param pretaxDeductions pretax deductions
     * @throws IllegalArgumentException check invalid parameters
     */
    public Employee(String name, String id, double payRate,
                    BigDecimal ytdEarnings, BigDecimal ytdTaxesPaid,
                    double pretaxDeductions, EmployeeType type) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be null or empty");
        }
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Employee id cannot be null or empty");
        }
        if (payRate < 0) {
            throw new IllegalArgumentException("Pay rate cannot be negative");
        }
        if (ytdEarnings == null || ytdEarnings.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("YTD earnings cannot be negative");
        }
        if (ytdTaxesPaid == null || ytdTaxesPaid.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("YTD taxes paid cannot be negative");
        }
        if (pretaxDeductions < 0) {
            throw new IllegalArgumentException("Pretax deductions cannot be negative");
        }
        if (type == null) {
            throw new IllegalArgumentException("Employee type cannot be null");
        }

        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
        this.type = type;


    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public double getPayRate() {
        return payRate;
    }

    @Override
    public double getYTDEarnings() {
        return ytdEarnings.doubleValue();
    }

    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid.doubleValue();
    }

    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    /**
     * the type of employee
     * @return this will return hourly or salary
     */
    @Override
    public String getEmployeeType() {
        return type.name();
    }

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked <= 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative");
        }


        /**
         * calculate gross pay
         */
        BigDecimal grossPay = calculateGrossPay(hoursWorked);

        /**
         * subtract pretax deductions
         */
        BigDecimal afterDeductions = grossPay.subtract(BigDecimal.valueOf(pretaxDeductions));

        /**
         * Calculate taxes
         */
        BigDecimal taxes = afterDeductions.multiply(TAX_RATE).setScale(SCALE, RoundingMode.HALF_UP);

        /**
         * Calculate net pay
         */
        BigDecimal netPay = afterDeductions.subtract(taxes);

        // YTD values
        ytdEarnings = ytdEarnings.add(grossPay);
        ytdTaxesPaid = ytdTaxesPaid.add(taxes);

        /**
         * Return a new PayStub object
         */
        return new PayStub(grossPay.doubleValue(), taxes.doubleValue());


    }

    /**
     * Calculates gross pay
     * @param hoursWorked hours worked
     * @return gross pay
     */
    protected abstract BigDecimal calculateGrossPay(double hoursWorked);

    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                type.name(), name, id, payRate, pretaxDeductions,
                ytdEarnings.doubleValue(), ytdTaxesPaid.doubleValue());
    }
}




