package test.java.smartoffice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    private Room room;

    @BeforeEach
    void setup() {
        room = new Room(1);
        room.setCapacity(10);
    }

    @Test
    void testBookAndCancel() {
        room.book("10:00", 30);
        assertTrue(room.isBooked());
        room.cancelBooking();
        assertFalse(room.isBooked());
    }
}
