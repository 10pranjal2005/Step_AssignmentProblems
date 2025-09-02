package AssignmentProblems;
class Patient {
    String patientId, name;
    static int totalPatients = 0;
    public Patient(String n) { patientId = "P" + (++totalPatients); name = n; }
}

class Doctor {
    String doctorId, name;
    static int totalDoctors = 0;
    public Doctor(String n) { doctorId = "D" + (++totalDoctors); name = n; }
}

class Appointment {
    String appId; Patient p; Doctor d;
    static int totalAppointments = 0;

    public Appointment(Patient p, Doctor d) {
        appId = "A" + (++totalAppointments);
        this.p = p; this.d = d;
    }

    public void show() {
        System.out.println("Appointment " + appId + ": " + p.name + " with Dr." + d.name);
    }
}

public class HospitalSystem {
    public static void main(String[] args) {
        Patient p1 = new Patient("Alice");
        Doctor d1 = new Doctor("Smith");
        Appointment a1 = new Appointment(p1, d1);
        a1.show();
    }
}
