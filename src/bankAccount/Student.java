package bankAccount;

import java.util.Arrays;

public class Student {
    int id;
    String name;
    int[] marks;
    double Average;

    public Student(String n) {
        this.id = (int)(Math.random() * 100);
        this.name = n;
    }

    double calculateAverage(int[] Marks) {
        double sum = 0;
        for (int i = 0; i < Marks.length; i++) {
            sum += Marks[i];
        }
        return sum / Marks.length;
    }

    void display() {
        System.out.println("Student Name: " + name);
        System.out.println("Student ID: " + id);
        System.out.printf("Average marks: %.2f%n", Average);
        System.out.println("Marks: " + Arrays.toString(marks));
        System.out.println();
    }

    public static void main(String[] args) {

        Student student1 = new Student("Punit");
        student1.marks = new int[]{85, 90, 78, 92, 88};
        student1.Average = student1.calculateAverage(student1.marks);
        student1.display();

        Student student2 = new Student("Yashdip");
        student2.marks = new int[]{75, 80, 68, 82, 78};
        student2.Average = student2.calculateAverage(student2.marks);
        student2.display();
    }
}
