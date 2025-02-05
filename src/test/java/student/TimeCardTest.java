package student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TimeCardTest {

    @Test
    public void testTimeCard() {
        ITimeCard timeCard = new TimeCard(12.5, "A1026");
        assertEquals(12.5, timeCard.getHoursWorked());
        assertEquals("A1026", timeCard.getEmployeeID());
    }

    @Test
    public void testInvalidHoursWorked() {
        assertThrows(IllegalArgumentException.class, () -> new TimeCard(-1, "A1026"));
    }
}