package org.Harel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
@Getter
@Setter
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
    public boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            return false
        } else {
            registeredCourses.remove(course);
            registeredStudents.remove(this);
        }
        return true;
    }
    public Student (String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%06d", nextId++);
        this.address = address;
        this.department = department;
        this.studentName = Util.toTitleCase(studentName);
        this.gender= gender;

        this.registeredCourses = new ArrayList<>();
    }
    public String toSimplifiedString() {
        return studentId + " " + studentName + " (" + department.getDepartmentName()+ ")";
    }

    @Override
    public String toString() {
        String courses ="[";
        for (Course c : registeredCourses) {
            courses += c.toSimplifiedString() + ", ";
        }
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gender=" + gender +
                ", address=" + address +
                ", department=" + department +
                ", registeredCourses=" + registeredCourses.c +
                '}';
    }
}
