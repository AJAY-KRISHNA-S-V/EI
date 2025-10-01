package main.java.smartoffice;

import java.util.*;
import java.util.logging.*;

public class OfficeConfiguration {
    private static final Logger logger = Logger.getLogger(OfficeConfiguration.class.getName());
    private static OfficeConfiguration instance;
    private final Map<Integer, Room> rooms = new HashMap<>();

    private OfficeConfiguration() {}

    public static synchronized OfficeConfiguration getInstance() {
        if (instance == null) {
            instance = new OfficeConfiguration();
        }
        return instance;
    }

    public synchronized void configureRooms(int count) {
        if (count <= 0) {
            logger.warning("Invalid room count: " + count);
            System.out.println("Error: Room count must be positive.");
            return;
        }
        rooms.clear();
        for (int i = 1; i <= count; i++) {
            rooms.put(i, new Room(i));
        }
        logger.info("Configured " + count + " rooms.");
        System.out.println("Office configured with " + count + " meeting rooms.");
    }

    public synchronized void setRoomCapacity(int roomId, int capacity) throws RoomNotFoundException {
        Room room = rooms.get(roomId);
        if (room == null) throw new RoomNotFoundException("Room " + roomId + " not found.");
        if (capacity <= 0) {
            logger.warning("Invalid capacity: " + capacity);
            System.out.println("Error: Capacity must be positive.");
            return;
        }
        room.setCapacity(capacity);
        logger.info("Room " + roomId + " capacity set to " + capacity);
        System.out.println("Room " + roomId + " maximum capacity set to " + capacity);
    }

    public synchronized void bookRoom(int roomId, String startTime, int duration)
            throws RoomNotFoundException, BookingException {
        Room room = rooms.get(roomId);
        if (room == null) throw new RoomNotFoundException("Room " + roomId + " not found.");
        if (duration <= 0) throw new BookingException("Duration must be positive.");

        // NOTE: This implementation uses simple single-booking per room.
        // Future: implement overlapping time checks and multiple bookings per room.
        if (room.isBooked()) {
            logger.warning("Room " + roomId + " booking failed: Already booked.");
            System.out.println("Room " + roomId + " is already booked.");
        } else {
            room.book(startTime, duration);
            logger.info("Room " + roomId + " booked successfully.");
        }
    }

    public synchronized void cancelBooking(int roomId) throws RoomNotFoundException {
        Room room = rooms.get(roomId);
        if (room == null) throw new RoomNotFoundException("Room " + roomId + " not found.");
        if (!room.isBooked()) {
            logger.warning("Cancel failed: Room " + roomId + " not booked.");
            System.out.println("Room " + roomId + " is not booked.");
        } else {
            room.cancelBooking();
            logger.info("Booking cancelled for Room " + roomId);
        }
    }

    public synchronized void addOccupants(int roomId, int count) throws RoomNotFoundException {
        Room room = rooms.get(roomId);
        if (room == null) throw new RoomNotFoundException("Room " + roomId + " not found.");
        room.setOccupants(count);
    }

    // helper for tests or future features
    public synchronized Room getRoom(int id) {
        return rooms.get(id);
    }
}
