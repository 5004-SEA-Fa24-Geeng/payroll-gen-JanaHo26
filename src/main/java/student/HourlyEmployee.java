package student;

import java.math.BigDecimal;


public class HourlyEmployee extends Employee {
    /** Overtime pay rate multiplier (1.5x regular pay). */
    private static final double OVERTIME_RATE = 1.5;

    /** Standard number of hours before overtime applied. */
    private static final double REGULAR_HOURS = 40.0;


    /**
     * creates a new hourly employee.
     * @param name employee name
     * @param id employee id
     * @param payRate hourly pay rate
     * @param ytdEarnings year to date earnings
     * @param ytdTaxesPaid year to date taxes paid
     * @param pretaxDeductions pretax deductions
     */
    public HourlyEmployee(String name, String id, double payRate,
                          double ytdEarnings, double ytdTaxesPaid,
                          double pretaxDeductions) {
        super(name, id, payRate,
                BigDecimal.valueOf(ytdEarnings),
                BigDecimal.valueOf(ytdTaxesPaid),
                pretaxDeductions, EmployeeType.HOURLY);
    }


    @Override
    protected BigDecimal calculateGrossPay(double hoursWorked) {
        BigDecimal regularPay;
        BigDecimal overtimePay = BigDecimal.ZERO;

        if (hoursWorked <= REGULAR_HOURS) {
            regularPay = BigDecimal.valueOf(getPayRate() * hoursWorked);
        } else {
            regularPay = BigDecimal.valueOf(getPayRate() * REGULAR_HOURS);
            double overtimeHours = hoursWorked - REGULAR_HOURS;
            overtimePay = BigDecimal.valueOf(overtimeHours * getPayRate() * OVERTIME_RATE);
        }

        return regularPay.add(overtimePay);
    }

}


