package org.Harel;

import java.util.ArrayList;

public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses = new ArrayList<>();
    static int nextId = 1;

    public enum Gender {
        MALE, FEMALE
    }
    public boolean registerCourse(Course course) {
        if (course == null) {
            return false;
        }
        course.getRegisteredStudents().add(this);

        if (registeredCourses.contains(course)) {
            return false;
        } else {
            registeredCourses.add(course);
        }
        for (Assignment a:  course.getAssignments()) {
            a.getScores().add(null);
        }
        return true;
    }

}
