package student;

import java.math.BigDecimal;

/**
 * pay stub including payment details for a pay period.
 */
public class PayStub implements IPayStub {
    /** The employee with the pay stub. */
    private final IEmployee employee;
    /** The net pay amount for the pay period. */
    private final BigDecimal netPay;
    /** The taxes paid amount for the pay period. */
    private final BigDecimal taxes;

    /**
     * create a new paystub.
     * @param employee the employee this paystub is for
     * @param netPay the net pay amount
     * @param taxes the taxes paid amount
     */
    public PayStub(IEmployee employee, BigDecimal netPay, BigDecimal taxes) {
        if (employee == null) {
            throw new IllegalArgumentException("employee must not be null");
        }
        if (netPay == null || netPay.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("pay must be greater than zero");
        }
        if (taxes == null || taxes.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("taxesPaid must be greater than zero");
        }
        this.employee = employee;
        this.netPay = netPay;
        this.taxes = taxes;
    }

    @Override
    public double getPay() {
        return netPay.doubleValue();
    }

    @Override
    public double getTaxesPaid() {
        return taxes.doubleValue();
    }

    @Override
    public String toCSV() {
        return String.format("%s,%.2f,%.2f,%.2f,%.2f",
                employee.getName(),
                getPay(),
                getTaxesPaid(),
                employee.getYTDEarnings(),
                employee.getYTDTaxesPaid()
        );
    }
}
