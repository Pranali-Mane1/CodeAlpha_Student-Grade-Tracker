
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double[] marks;

    Student(String name, double[] marks) {
        this.name = name;
        this.marks = marks;
    }

    double calculateAverage() {
        double total = 0;

        for (double mark : marks) {
            total += mark;
        }

        return total / marks.length;
    }

    double getHighestMark() {
        double highest = marks[0];

        for (double mark : marks) {
            if (mark > highest) {
                highest = mark;
            }
        }

        return highest;
    }

    double getLowestMark() {
        double lowest = marks[0];

        for (double mark : marks) {
            if (mark < lowest) {
                lowest = mark;
            }
        }

        return lowest;
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n <= 0) {
            System.out.println("Number of students must be positive.");
            sc.close();
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1));

            System.out.print("Enter student name: ");
            String name = sc.nextLine().trim();

            while (name.isEmpty()) {
                System.out.print("Name cannot be empty. Enter name: ");
                name = sc.nextLine().trim();
            }

            double[] marks = new double[3];

            for (int j = 0; j < 3; j++) {
                while (true) {
                    System.out.print("Enter marks for subject "
                            + (j + 1) + " (0-100): ");

                    if (!sc.hasNextDouble()) {
                        System.out.println("Please enter a number.");
                        sc.nextLine();
                        continue;
                    }

                    double mark = sc.nextDouble();

                    if (mark >= 0 && mark <= 100) {
                        marks[j] = mark;
                        break;
                    }

                    System.out.println("Marks must be between 0 and 100.");
                }
            }

            sc.nextLine();
            students.add(new Student(name, marks));
        }

        System.out.println("\n========== STUDENT REPORT ==========");

        double totalAverage = 0;
        double highestScore = Double.MIN_VALUE;
        double lowestScore = Double.MAX_VALUE;
        String highestStudent = "";
        String lowestStudent = "";

        for (Student student : students) {
            double average = student.calculateAverage();

            System.out.println("\nName: " + student.name);
            System.out.printf("Average: %.2f%n", average);
            System.out.println("Highest mark: " + student.getHighestMark());
            System.out.println("Lowest mark: " + student.getLowestMark());

            totalAverage += average;

            if (average > highestScore) {
                highestScore = average;
                highestStudent = student.name;
            }

            if (average < lowestScore) {
                lowestScore = average;
                lowestStudent = student.name;
            }
        }

        System.out.println("\n========== CLASS SUMMARY ==========");
        System.out.printf("Class average: %.2f%n",
                totalAverage / students.size());
        System.out.println("Top student: " + highestStudent
                + " (" + highestScore + ")");
        System.out.println("Lowest average student: " + lowestStudent
                + " (" + lowestScore + ")");

        sc.close();
    }
}
