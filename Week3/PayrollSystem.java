package AssignmentProblems;
class Employee {
    String empId, name, dept;
    double baseSalary;
    boolean[] attendance = new boolean[30];
    static int totalEmployees = 0;

    public Employee(String n, String d, double sal) {
        empId = "E" + (++totalEmployees);
        name = n; dept = d; baseSalary = sal;
    }

    public void markAttendance(int day, boolean present) {
        attendance[day-1] = present;
    }

    public double calculateSalary() {
        int days = 0;
        for (boolean p : attendance) if (p) days++;
        return baseSalary / 30 * days;
    }

    public void paySlip() {
        System.out.println(empId + " | " + name + " | Salary: " + calculateSalary());
    }
}

public class PayrollSystem {
    public static void main(String[] args) {
        Employee e1 = new Employee("Alice", "IT", 30000);
        e1.markAttendance(1, true);
        e1.markAttendance(2, false);
        e1.paySlip();
    }
}
