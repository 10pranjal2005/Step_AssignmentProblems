package AssignmentProblems;
class Room {
    String roomNumber, roomType;
    double pricePerNight;
    boolean isAvailable = true;

    public Room(String no, String type, double price) {
        roomNumber = no; roomType = type; pricePerNight = price;
    }
}

class Guest {
    String guestId, guestName;
    static int totalGuests = 0;
    public Guest(String name) {
        guestId = "G" + (++totalGuests);
        guestName = name;
    }
}

class Booking {
    String bookingId;
    Guest guest;
    Room room;
    double totalAmount;
    static int totalBookings = 0;

    public Booking(Guest g, Room r, int days) {
        bookingId = "B" + (++totalBookings);
        guest = g; room = r;
        if (r.isAvailable) {
            r.isAvailable = false;
            totalAmount = r.pricePerNight * days;
        }
    }

    public void display() {
        System.out.println("Booking " + bookingId + ": " + guest.guestName +
            " Room " + room.roomNumber + " Amount " + totalAmount);
    }
}

public class HotelSystem {
    public static void main(String[] args) {
        Room r1 = new Room("101", "Deluxe", 2000);
        Guest g1 = new Guest("Alice");
        Booking b1 = new Booking(g1, r1, 3);
        b1.display();
    }
}
