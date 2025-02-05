package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * salaried employee who receives fixed periodic payments.
 */
public class SalaryEmployee extends Employee {
    /** Number of pay periods per year for salary calculations. */
    private static final int PAYMENT_PERIOD = 24;

    /**
     * Creates a new salaried employee.
     * @param name employee name
     * @param id employee id
     * @param payRate annual salary rate
     * @param ytdEarnings year to date earnings
     * @param ytdTaxesPaid year to date taxes paid
     * @param pretaxDeductions pretax deductions
     */
    public SalaryEmployee(String name, String id, double payRate,
                          double ytdEarnings, double ytdTaxesPaid,
                          double pretaxDeductions) {
        super(name, id, payRate,
                BigDecimal.valueOf(ytdEarnings),
                BigDecimal.valueOf(ytdTaxesPaid),
                pretaxDeductions, EmployeeType.Salary);
    }

    @Override
    protected BigDecimal calculateGrossPay(double hoursWorked) {
        BigDecimal salary = BigDecimal.valueOf(getPayRate());
        BigDecimal grossPay = salary.divide(BigDecimal.valueOf(PAYMENT_PERIOD), SCALE, RoundingMode.HALF_UP);
        return grossPay;
    }
}
