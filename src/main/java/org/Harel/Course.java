package org.Harel;

import java.util.ArrayList;

public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignment = new ArrayList<>();
    private ArrayList<Student> registeredStudents = new ArrayList<>();
    private static int nextId = 1;

    public boolean isAssignmentWeightValid() {
        int sum = 0;
        for ( Assignment a : assignment) {
            sum += a.getWeight();
        }
        return sum ==100;
    }
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
}
