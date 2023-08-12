import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ParkingSpot {
    private int spotNumber;
    private boolean isOccupied;

    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.isOccupied = false;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy() {
        isOccupied = true;
    }

    public void vacate() {
        isOccupied = false;
    }
}

class ParkingLot {
    private Map<Integer, ParkingSpot> spots;

    public ParkingLot(int capacity)
    {
        spots = new HashMap<>();
        for (int i = 1; i <= capacity; i++) {
            spots.put(i, new ParkingSpot(i));
        }
    }

    public boolean isSpotAvailable(int spotNumber)
    {
        ParkingSpot spot = spots.get(spotNumber);
        return spot != null && !spot.isOccupied();
    }

    public void occupySpot(int spotNumber)
    {
        ParkingSpot spot = spots.get(spotNumber);
        if (spot != null) {
            spot.occupy();
        }
    }

    public void vacateSpot(int spotNumber) {
        ParkingSpot spot = spots.get(spotNumber);
        if (spot != null) {
            spot.vacate();
        }
    }
}

class Booking {
    private int spotNumber;
    private LocalDateTime startTime;

    public Booking(int spotNumber, LocalDateTime startTime) {
        this.spotNumber = spotNumber;
        this.startTime = startTime;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}

class ParkingSystem {
    private ParkingLot parkingLot;
    private Map<String, Booking> userBookings;

    public ParkingSystem(int capacity) {
        parkingLot = new ParkingLot(capacity);
        userBookings = new HashMap<>();
    }

    public boolean bookSpot(String username, int spotNumber) {
        if (parkingLot.isSpotAvailable(spotNumber)) {
            parkingLot.occupySpot(spotNumber);
            userBookings.put(username, new Booking(spotNumber, LocalDateTime.now()));
            return true;
        }
        return false;
    }

    public void cancelBooking(String username) {
        Booking booking = userBookings.get(username);
        if (booking != null) {
            parkingLot.vacateSpot(booking.getSpotNumber());
            userBookings.remove(username);
        }
    }

    public Booking getBooking(String username) {
        return userBookings.get(username);
    }
}

public class parking {
    public static void main(String[] args)
    {
        System.out.println("*********************************************************************************************");
        System.out.println("*******************************WELCOME TO ONLINE PARKING SYSTEM******************************");
        System.out.println("*********************************************************************************************");


        Scanner scanner = new Scanner(System.in);
        ParkingSystem parkingSystem = new ParkingSystem(10);



        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Book a parking spot");
            System.out.println("2. Cancel booking");
            System.out.println("3. Check booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter spot number to book: ");
                    int spotNumber = scanner.nextInt();
                    scanner.nextLine();

                    if (parkingSystem.bookSpot(username, spotNumber)) {
                        System.out.println("Parking spot booked successfully.");
                    } else {
                        System.out.println("Spot is not available.");
                    }
                    break;

                case 2:
                    System.out.print("Enter your username: ");
                    String cancelUsername = scanner.nextLine();
                    parkingSystem.cancelBooking(cancelUsername);
                    System.out.println("Booking canceled.");
                    break;

                case 3:
                    System.out.print("Enter your username: ");
                    String checkUsername = scanner.nextLine();
                    Booking userBooking = parkingSystem.getBooking(checkUsername);

                    if (userBooking != null) {
                        System.out.println("Your booking details:");
                        System.out.println("Spot Number: " + userBooking.getSpotNumber());
                        System.out.println("Start Time: " + userBooking.getStartTime());
                    } else {
                        System.out.println("No booking found for this user.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
