package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SalaryEmployeeTest {
    private SalaryEmployee salaryEmployee;

    @BeforeEach
    void setUp() {
        salaryEmployee = new SalaryEmployee("Nami",
                "s193",
                200000,
                17017,
                4983,
                1000);
    }

        @Test
        void getName () {
        assertEquals("Nami", salaryEmployee.getName());
        }

        @Test
        void getID () {
        assertEquals("s193", salaryEmployee.getID());
        }

        @Test
        void getPayRate () {
        assertEquals(200000, salaryEmployee.getPayRate());
        }

        @Test
        void getYTDEarnings () {
        assertEquals(17017, salaryEmployee.getYTDEarnings());
        }

        @Test
        void getYTDTaxesPaid () {
        assertEquals(4983, salaryEmployee.getYTDTaxesPaid());
        }

        @Test
        void getPretaxDeductions () {
        assertEquals(1000, salaryEmployee.getPretaxDeductions());
        }

        @Test
        void getEmployeeType () {
        assertEquals("Salary", salaryEmployee.getEmployeeType());
        }

        @Test
        void runPayroll () {
            IPayStub payStub = salaryEmployee.runPayroll(40);
            double expected = 200000 / 24.0;
            assertEquals(expected, payStub.getPay(), 0.01);
        }

        @Test
        void calculateGrossPay () {
            BigDecimal grossPay = salaryEmployee.calculateGrossPay(40);
            double expected = 200000 / 24.0;
            assertEquals(expected, grossPay.doubleValue(), 0.01);
        }
    }
