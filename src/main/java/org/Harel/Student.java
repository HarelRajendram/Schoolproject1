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

    /**
     *register the student to a course
     * @param course is the course where the student registers to
     * @return boolean value if student is registered to course or already in a course
     */
    public boolean registerCourse(Course course) {
        if (course == null)
            return false;

        if (registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.add(course);
        course.getRegisteredStudents().add(this);

        for (Assignment a : course.getAssignment()) {
            a.getScores().add(null);
        }
        course.getFinalScores().add(null);
        return true;
    }
    /**
     * Drops a course for the student
     * @param course is the course where we remove the student from
     * @return a boolean value to remove the student from the course
     */
    public boolean dropCourse(Course course) {
        if (course == null || !registeredCourses.contains(course)) {
            return false;
        }
        int index = course.getRegisteredStudents().indexOf(this);
        registeredCourses.remove(course);
        course.getRegisteredStudents().remove(this);

        for (Assignment a : course.getAssignment()) {
            a.getScores().remove(index);
        }
        course.getFinalScores().remove(index);
        return true;
    }

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%06d", nextId++);
        this.address = address;
        this.department = department;
        this.studentName = Util.toTitleCase(studentName);
        this.gender = gender;

        this.registeredCourses = new ArrayList<>();
    }

    public String toSimplifiedString() {
        return studentId + " " + studentName + " (" + department.getDepartmentName() + ")";
    }

    @Override
    public String toString() {
        String result = "Student ID: " + studentId +
                ", Name: " + studentName +
                ", Gender: " + gender +
                ", Address: " + address +
                ", Department: " + department.getDepartmentName() +
                "\nRegistered courses: ";

        if (registeredCourses.isEmpty()) {
            result += "None";
        } else {
            for (int i = 0; i < registeredCourses.size(); i++) {
                Course course = registeredCourses.get(i);
                result += course.getCourseId() + " - " +
                        course.getCourseName() + " (" +
                        course.getDepartment().getDepartmentName() + ")";
                if (i < registeredCourses.size() - 1) {
                    result += ", ";
                }
            }
        }
        return result;
    }
}