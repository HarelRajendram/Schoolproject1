package org.Harel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

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

    /**
     *checks if the sum of weights of all assignments of that course equals to 100%
     * @return a boolean value if sum is equal to 100
     */
    public boolean isAssignmentWeightValid() {
        int sum = 0;
        for ( Assignment a : assignment) {
            sum += a.getWeight();
        }
        return sum == 100;
    }

    /**
     *adds a student to the student list of the course
     * @param student
     * @return
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
    public int[] calcStudentsAverage() {
        int numStudent = registeredStudents.size();
        int[] averages = new int[numStudent];
        for (int i = 0; i < numStudent; i++) {
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
    public void generateScores() {
        Random rand = new Random();
        for (Assignment a : assignment) {
            a.getScores().clear();

            for (int i = 0; i < registeredStudents.size();i++) {
                int score = rand.nextInt(0, 100 + 1);
                a.getScores().add(score);
            }
        }
        finalScores.clear();

        for (int i = 0; i < registeredStudents.size(); i++) {
            double total = 0;

            for (Assignment a : assignment) {
                int score = a.getScores().get(i);
                total += score * (a.getWeight() / 100.0);
            }
            finalScores.add((int) Math.round(total));
        }
    }
    public void displayScores() {
        System.out.println("Course:" + courseName + courseId);
        System.out.printf("%-20s", "");
        for (Assignment a : assignment) {
            System.out.printf("%-15s", a.getAssignmentName());
        }

        System.out.printf("%-15s%n", "FinalS core");

        for (int i = 0; i< registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);

            System.out.printf("%-20s", student.getStudentName());

            for (Assignment a : assignment) {
                Integer score = a.getScores().get(i);
                System.out.printf("%-15s", score == null ? "-" : score.toString());
            }
            System.out.printf("");
        }

        System.out.printf("%-20s", "Average");
        for (Assignment a : assignment) {
            int totalScore = 0;
            int numAssignments = 0;

            for (Integer score : a.getScores()) {
                if (score != null) {
                    totalScore += score;
                    numAssignments ++;
                }
            }
            int avg = numAssignments == 0 ? 0 : Math.round(totalScore / numAssignments);
            System.out.printf("%-15d", avg);
        }
        System.out.println();
    }
    public String toSimplifiedString() {
        return courseId + " " + courseName + " " + credits + " (" + department.getDepartmentName() + ")";
    }
    @Override
    public String toString() {
        String result = "Course ID: " + courseId +
                ", Name: " + courseName +
                ", Credits: " + credits +
                ", Department: " + department.getDepartmentName() +
                "\n";

        result += "Assignments: ";
        for (Assignment a : assignment) {
            result += a.getAssignmentName() + " (" + a.getWeight() + "%), ";
        }
        result += "\n";

        result += "Students: ";
        for (Student student : registeredStudents) {
            result += student.getStudentId() + " - " + student.getStudentName() +
                    " (" + student.getDepartment().getDepartmentName() + "), ";
        }
        result += "\n";

        result += "Assignment weights valid: " + isAssignmentWeightValid();

        return result;
    }

    public Course(String courseId, String courseName, double credits, Department department, ArrayList<Assignment> assignment, ArrayList<Student> registeredStudents) {
        this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.assignment = assignment;
        this.registeredStudents = registeredStudents;
    }
}
