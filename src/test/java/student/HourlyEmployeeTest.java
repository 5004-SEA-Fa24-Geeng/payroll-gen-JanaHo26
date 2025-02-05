package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class HourlyEmployeeTest {
    private HourlyEmployee hourlyEmployee;

    @BeforeEach
    void setUp() {
        hourlyEmployee = new HourlyEmployee("Light Yagami",
                "x101",
                25.00,
                10000.0,
                2265,
                0);
    }

    @Test
    void getName() {
        assertEquals("Light Yagami", hourlyEmployee.getName());
    }

    @Test
    void getID() {
        assertEquals("x101", hourlyEmployee.getID());
    }

    @Test
    void getPayRate() {
        assertEquals(25.00, hourlyEmployee.getPayRate());
    }

    @Test
    void getYTDEarnings() {
        assertEquals(10000.0, hourlyEmployee.getYTDEarnings());
    }

    @Test
    void getYTDTaxesPaid() {
        assertEquals(2265, hourlyEmployee.getYTDTaxesPaid());
    }

    @Test
    void getPretaxDeductions() {
        assertEquals(0, hourlyEmployee.getPretaxDeductions());
    }

    @Test
    void getEmployeeType() {
        assertEquals("HOURLY", hourlyEmployee.getEmployeeType());
    }

    @Test
    void runPayroll() {
        IPayStub payStub = hourlyEmployee.runPayroll(40);
        assertEquals(1000.0, payStub.getPay(), 0.01);  // 40hours * $25
    }

    @Test
    void calculateGrossPay() {
        BigDecimal regularPay = hourlyEmployee.calculateGrossPay(40);
        assertEquals(1000.0, regularPay.doubleValue(), 0.01);

        //25*1.5*10 = 375
        //1000+375 = 1375
        BigDecimal overtimePay = hourlyEmployee.calculateGrossPay(50);
        assertEquals(1375.0, overtimePay.doubleValue(), 0.01);
    }
}