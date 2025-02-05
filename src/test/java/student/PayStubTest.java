package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.channels.IllegalChannelGroupException;

import static org.junit.jupiter.api.Assertions.*;

class PayStubTest {
    private PayStub payStub;
    private HourlyEmployee employee;

    @BeforeEach
    void setUp() {
        employee = new HourlyEmployee("Jana", "x13", 20.0, 0.0, 0.0 ,0.0);
        payStub = new PayStub(employee, new BigDecimal("1000.0"), new BigDecimal("300.0"));
    }

    @Test
    void getPay() {
        assertEquals(1000.0, payStub.getPay());
    }

    @Test
    void getTaxesPaid() {
        assertEquals(300.0, payStub.getTaxesPaid());
    }


    @Test
    void negativePay() {
        assertThrows(IllegalArgumentException.class, () -> new PayStub(employee, new BigDecimal("-1000.0"), new BigDecimal("-300.0")), "Pay must be greater than o.");
    }

    @Test
    void negativeTaxesPaid() {
        assertThrows(IllegalArgumentException.class, () -> new PayStub(employee, new BigDecimal("1000.0"), new BigDecimal("-300.0")), "Taxes must be greater than o.");
    }
}