package student;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class HourlyEmployee extends Employee {
    /** Overtime pay rate multiplier (1.5x regular pay). */
    private static final BigDecimal OVERTIME_RATE = new BigDecimal(1.5);

    /** Standard number of hours before overtime applied. */
    private static final BigDecimal REGULAR_HOURS = new BigDecimal(40.0);


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
        BigDecimal hoursWorkedBigDecimal = new BigDecimal(String.valueOf(hoursWorked));
        BigDecimal payRate = BigDecimal.valueOf(getPayRate());

        if (hoursWorkedBigDecimal.compareTo(REGULAR_HOURS) <= 0) {
            return payRate.multiply(hoursWorkedBigDecimal)
                    .setScale(SCALE, RoundingMode.HALF_UP);
        }

        // Calculate regular pay
        BigDecimal regularPay = payRate.multiply(REGULAR_HOURS);

        // Calculate overtime pay
        BigDecimal overtimeHours = hoursWorkedBigDecimal.subtract(REGULAR_HOURS);
        BigDecimal overtimePayRate = payRate.multiply(OVERTIME_RATE);
        BigDecimal overtimePay = overtimeHours.multiply(overtimePayRate);

        return regularPay.add(overtimePay).setScale(SCALE, RoundingMode.HALF_UP);
    }
}


//    @Override
//    protected BigDecimal calculateGrossPay(double hoursWorked) {
//        BigDecimal regularPay;
//        BigDecimal overtimePay = BigDecimal.ZERO;
//
//        if (hoursWorked <= REGULAR_HOURS) {
//            regularPay = BigDecimal.valueOf(getPayRate() * hoursWorked)
//                        .setScale(SCALE, RoundingMode.HALF_UP);
//        } else {
//            regularPay = BigDecimal.valueOf(getPayRate() * REGULAR_HOURS)
//                        .setScale(SCALE, RoundingMode.HALF_UP);
//            double overtimeHours = hoursWorked - REGULAR_HOURS;
//            overtimePay = BigDecimal.valueOf(overtimeHours * getPayRate() * OVERTIME_RATE)
//                        .setScale(SCALE, RoundingMode.HALF_UP);
//        }
//
//        return regularPay.add(overtimePay).setScale(SCALE, RoundingMode.HALF_UP);
//    }
//
//}


