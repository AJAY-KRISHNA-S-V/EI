package test.java.smartoffice;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OfficeConfigurationTest {
    private OfficeConfiguration office;

    @BeforeEach
    void setup() {
        office = OfficeConfiguration.getInstance();
        office.configureRooms(3);
    }

    @Test
    void testConfigureRooms() {
        assertDoesNotThrow(() -> office.setRoomCapacity(1, 10));
    }

    @Test
    void testBookRoomInvalidDuration() {
        assertThrows(BookingException.class,
                () -> office.bookRoom(1, "09:00", -10));
    }
}

