package AssignmentProblems;
class Student {
    String studentId, studentName, className;
    double[][] marks; // subjects x tests
    static int totalStudents = 0;

    public Student(String name, String cls, int subjects, int tests) {
        studentId = "S" + (++totalStudents);
        studentName = name; className = cls;
        marks = new double[subjects][tests];
    }

    public void addMarks(int subj, int test, double m) {
        marks[subj][test] = m;
    }

    public double calculateGPA() {
        double sum = 0; int count = 0;
        for (double[] subj : marks) for (double m : subj) { sum += m; count++; }
        return sum / count / 10; // GPA scale 0-10
    }

    public void report() {
        System.out.println(studentName + " GPA: " + calculateGPA());
    }
}

public class GradeSystem {
    public static void main(String[] args) {
        Student s1 = new Student("Alice", "10A", 3, 2);
        s1.addMarks(0,0,90); s1.addMarks(1,0,80); s1.addMarks(2,0,70);
        s1.report();
    }
}
