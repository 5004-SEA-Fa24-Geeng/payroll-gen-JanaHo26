package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.channels.IllegalChannelGroupException;

import static org.junit.jupiter.api.Assertions.*;

class PayStubTest {
    private PayStub payStub;

    @BeforeEach
    void setUp() {
        payStub = new PayStub(1000.0, 300.0);
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
        assertThrows(IllegalArgumentException.class, () -> new PayStub(-1000.0, -300.0), "Pay must be greater than 0");
    }

    @Test
    void negativeTaxesPaid() {
        assertThrows(IllegalArgumentException.class, () -> new PayStub(1000.0, -300.0), "Tax paid must be greater than zero.");
    }
}