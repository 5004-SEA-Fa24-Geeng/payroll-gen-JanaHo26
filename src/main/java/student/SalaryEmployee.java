package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalaryEmployee extends Employee {
    private static final int PAYMENT_PERIOD = 24;

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
