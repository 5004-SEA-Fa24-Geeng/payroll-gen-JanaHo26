package student;

/**
 * pay stub including payment details for a pay period.
 */
public class PayStub implements IPayStub {
    /** The gross pay amount for the pay period. */
    private double pay;
    /** The taxes paid amount for the pay period. */
    private double taxesPaid;

    /**
     * create a new paystub.
     * @param pay the gross pay amount
     * @param taxesPaid the taxes paid amount
     */
    public PayStub(double pay, double taxesPaid) {
        if (pay < 0) {
            throw new IllegalArgumentException("pay must be greater than zero");
        }
        if (taxesPaid < 0) {
            throw new IllegalArgumentException("taxesPaid must be greater than zero");
        }
        this.pay = pay;
        this.taxesPaid = taxesPaid;
    }

    @Override
    public double getPay() {
        return pay;
    }

    @Override
    public double getTaxesPaid() {
        return taxesPaid;
    }

    @Override
    public String toCSV() {
        return String.format("%.2f,%.2f", pay, taxesPaid);
    }
}
