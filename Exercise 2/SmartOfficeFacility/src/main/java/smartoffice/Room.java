package main.java.smartoffice;

import java.util.logging.*;

public class Room implements RoomObserver {
    private static final Logger logger = Logger.getLogger(Room.class.getName());

    private final int roomId;
    private int capacity;
    private boolean booked;
    private boolean occupied;
    private int occupants;
    private String bookingTime;

    private final LightSystem lightSystem;
    private final ACSystem acSystem;

    public Room(int id) {
        this.roomId = id;
        this.capacity = 0;
        this.booked = false;
        this.occupied = false;
        this.occupants = 0;
        this.lightSystem = new LightSystem();
        this.acSystem = new ACSystem();
    }

    public void setCapacity(int cap) {
        this.capacity = cap;
    }

    public void book(String start, int duration) {
        this.booked = true;
        this.bookingTime = start;
        System.out.println("Room " + roomId + " booked from " + start + " for " + duration + " minutes.");
    }

    public void cancelBooking() {
        this.booked = false;
        this.bookingTime = null;
        System.out.println("Booking for Room " + roomId + " cancelled successfully.");
    }

    public boolean isBooked() {
        return booked;
    }

    public void setOccupants(int num) {
        if (capacity <= 0) {
            logger.warning("Attempt to set occupants when capacity not configured for room " + roomId);
            System.out.println("Error: Room capacity is not set. Configure capacity first.");
            return;
        }

        if (num >= 2 && num <= capacity) {
            this.occupied = true;
            this.occupants = num;
            notifySystems(true);
            logger.info("Room " + roomId + " occupied by " + num + " persons.");
            System.out.println("Room " + roomId + " is now occupied by " + num + " persons. AC and lights turned on.");
        } else if (num == 0) {
            this.occupied = false;
            this.occupants = 0;
            notifySystems(false);
            logger.info("Room " + roomId + " is now unoccupied.");
            System.out.println("Room " + roomId + " is now unoccupied. AC and lights turned off.");
        } else {
            logger.warning("Invalid occupancy set for room " + roomId + ": " + num);
            System.out.println("Room " + roomId + " occupancy insufficient or exceeds capacity.");
        }
    }

    @Override
    public void notifySystems(boolean occupied) {
        if (occupied) {
            lightSystem.turnOn();
            acSystem.turnOn();
        } else {
            lightSystem.turnOff();
            acSystem.turnOff();
        }
    }
}
