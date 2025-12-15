package org.Harel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
@Getter
@Setter
public class Course {

    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignment = new ArrayList<>();
    private ArrayList<Student> registeredStudents = new ArrayList<>();
    private static int nextId = 1;
    private ArrayList<Integer> finalScores = new ArrayList<>();

    /**
     * Checks if the sum of weights of all assignments equals 100%.
     */
    public boolean isAssignmentWeightValid() {
        int sum = 0;
        for (Assignment a : assignment) {
            sum += a.getWeight();
        }
        return sum == 100;
    }

    /**
     * Registers a student into the course.
     */
    public boolean registerStudent(Student student) {
        if (student == null) {
            return false;
        }
        if (registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);

        for (Assignment a : assignment) {
            a.getScores().add(null);
        }

        finalScores.add(null);
        return true;
    }

    /**
     * Calculates weighted averages for all students.
     */
    public int[] calcStudentsAverage() {
        int numStudents = registeredStudents.size();
        int[] averages = new int[numStudents];

        for (int i = 0; i < numStudents; i++) {
            double total = 0;

            for (Assignment a : assignment) {
                Integer score = a.getScores().get(i);
                if (score != null) {
                    total += score * (a.getWeight() / 100.0);
                }
            }

            averages[i] = (int) Math.round(total);
        }

        return averages;
    }

    /**
     * Adds a new assignment to the course.
     */
    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        if (assignmentName == null || weight < 0 || maxScore < 0) {
            return false;
        }

        Assignment a = new Assignment(assignmentName, weight, maxScore);
        assignment.add(a);

        for (int i = 0; i < registeredStudents.size(); i++) {
            a.getScores().add(null);
        }

        return true;
    }

    /**
     * Generates random scores for each assignment and student.
     */
    public void generateScores() {
        for (Assignment a : assignment) {
            a.generateRandomScore();   // Assumes this fills scores for all students
        }
        calcStudentsAverage();
    }

    /**
     * Displays the scores of the course in a table.
     */
    public void displayScores() {
        System.out.println("Course: " + courseName + " (" + courseId + ")");

        System.out.printf("%-20s", "");
        for (Assignment a : assignment) {
            System.out.printf("%-15s", a.getAssignmentName());
        }
        System.out.printf("%-15s%n", "Final Score");

        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);

            System.out.printf("%-20s", student.getStudentName());

            for (Assignment a : assignment) {
                Integer score = a.getScores().get(i);
                System.out.printf("%-15s", score == null ? "-" : score.toString());
            }

            System.out.println();
        }
        System.out.printf("%-20s", "Average");

        for (Assignment a : assignment) {
            int total = 0;
            int count = 0;

            for (Integer score : a.getScores()) {
                if (score != null) {
                    total += score;
                    count++;
                }
            }
            int avg = count == 0 ? 0 : Math.round(total / count);
            System.out.printf("%-15d", avg);
        }
        System.out.println();
    }

    /**
     * Simplified string representation.
     */
    public String toSimplifiedString() {
        return courseId + " " + courseName + " " + credits +
                " (" + department.getDepartmentName() + ")";
    }

    @Override
    public String toString() {
        String result = "Course ID: " + courseId +
                ", Name: " + courseName +
                ", Credits: " + credits +
                ", Department: " + department.getDepartmentName() +
                "\nAssignments: ";

        for (Assignment a : assignment) {
            result += a.getAssignmentName() + " (" + a.getWeight() + "%), ";
        }

        result += "\nStudents: ";

        for (Student s : registeredStudents) {
            result += s.getStudentId() + " - " + s.getStudentName() +
                    " (" + s.getDepartment().getDepartmentName() + "), ";
        }

        result += "\nAssignment weights valid: " + isAssignmentWeightValid();

        return result;
    }

    public Course(String courseName, double credits, Department department) {
        this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.assignment = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
        this.finalScores = new ArrayList<>();
    }
}
