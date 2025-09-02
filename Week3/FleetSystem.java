package AssignmentProblems;
class Vehicle {
    String id, brand;
    double mileage;
    static int totalVehicles = 0;

    public Vehicle(String b) {
        id = "V" + (++totalVehicles);
        brand = b;
    }

    public void updateMileage(double m) { mileage += m; }
}

class Car extends Vehicle { public Car(String b){super(b);} }
class Bus extends Vehicle { public Bus(String b){super(b);} }
class Truck extends Vehicle { public Truck(String b){super(b);} }

public class FleetSystem {
    public static void main(String[] args) {
        Car c1 = new Car("Toyota");
        Bus b1 = new Bus("Volvo");
        c1.updateMileage(100);
        System.out.println(c1.id + " " + c1.brand + " Mileage: " + c1.mileage);
    }
}
