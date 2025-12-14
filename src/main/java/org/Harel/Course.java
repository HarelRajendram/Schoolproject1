package org.Harel;

import java.util.ArrayList;
import java.util.Random;

public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignment = new ArrayList<>();
    private ArrayList<Student> registeredStudents = new ArrayList<>();
    private static int nextId = 1;

    /**
     *
     * @return
     */
    public boolean isAssignmentWeightValid() {
        int sum = 0;
        for ( Assignment a : assignment) {
            sum += a.getWeight();
        }
        return sum == 100;
    }

    /**
     *
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
        for (int i = 0; i <= numStudent; i++) {
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
                int score = rand.nextInt(0, 100);
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
    }
}
