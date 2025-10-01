package main.java.smartoffice;

import java.util.Scanner;
import java.util.logging.*;

public class SmartOfficeApp {
    private static final Logger logger = Logger.getLogger(SmartOfficeApp.class.getName());

    public static void main(String[] args) {
        Logger root = Logger.getLogger("");
        for (Handler h : root.getHandlers()) {
            if (h instanceof ConsoleHandler) {
                h.setLevel(Level.INFO);
            }
        }
        logger.setLevel(Level.INFO);

        OfficeConfiguration office = OfficeConfiguration.getInstance();
        Scanner sc = new Scanner(System.in);

        logger.info("Smart Office Facility Manager started.");
        System.out.println("=== Smart Office Facility Manager ===");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Configure Rooms");
            System.out.println("2. Set Room Capacity");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Add Occupants");
            System.out.println("6. Exit");

            try {
                System.out.print("Enter option: ");
                int choice = Integer.parseInt(sc.nextLine().trim());
                switch (choice) {
                    case 1:
                        System.out.print("Enter number of meeting rooms: ");
                        int count = Integer.parseInt(sc.nextLine().trim());
                        office.configureRooms(count);
                        break;
                    case 2:
                        System.out.print("Enter room number: ");
                        int r1 = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter capacity: ");
                        int cap = Integer.parseInt(sc.nextLine().trim());
                        office.setRoomCapacity(r1, cap);
                        break;
                    case 3:
                        System.out.print("Enter room number: ");
                        int r2 = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter start time (HH:MM): ");
                        String start = sc.nextLine().trim();
                        System.out.print("Enter duration (minutes): ");
                        int dur = Integer.parseInt(sc.nextLine().trim());
                        office.bookRoom(r2, start, dur);
                        break;
                    case 4:
                        System.out.print("Enter room number: ");
                        int r3 = Integer.parseInt(sc.nextLine().trim());
                        office.cancelBooking(r3);
                        break;
                    case 5:
                        System.out.print("Enter room number: ");
                        int r4 = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter number of occupants: ");
                        int occ = Integer.parseInt(sc.nextLine().trim());
                        office.addOccupants(r4, occ);
                        break;
                    case 6:
                        logger.info("Application exited successfully.");
                        System.out.println("Exiting Smart Office...");
                        sc.close();
                        return;
                    default:
                        logger.warning("Invalid menu choice: " + choice);
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error in main loop", e);
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
