package org.Harel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    Department dept = new Department("Math");
    Address address = new Address(41, "Coolbrook", "Montreal", Address.Province.PE, "A3L2Z0");

    @Test
    @DisplayName("Course register -> true")
    void testRegisterCourse1() {
        Student student = new Student("Carl Anthony", Student.Gender.MALE, address, dept);
        Course c = new Course("Discrete", 2.0, dept);

        c.addAssignment("D1", 100.0, 100);
        c.setRegisteredStudents(new ArrayList<>());

        boolean result = student.registerCourse(c);
        assertTrue(result);
    }

    @Test
    @DisplayName("Registering the same course twice should return false")
    void testRegisterCourse2() {
        Student student = new Student("Mintou Plip", Student.Gender.FEMALE, address, dept);
        Course c = new Course("Discrete", 1.0, dept);

        c.addAssignment("D1", 100.0, 100);
        c.setRegisteredStudents(new ArrayList<>());

        student.registerCourse(c);

        boolean result = student.registerCourse(c);
        assertFalse(result);
    }

    @Test
    @DisplayName("Registering a second, different course should return true")
    void testRegisterCourse3() {
        Student student = new Student("Doe Bark", Student.Gender.FEMALE, address, dept);
        Course c1 = new Course("Business", 9.0, dept);
        Course c2 = new Course("Math", 3.0, dept);

        c1.addAssignment("A1", 100.0, 100);
        c2.addAssignment("N1", 100.0, 100);

        c1.setRegisteredStudents(new ArrayList<>());
        c2.setRegisteredStudents(new ArrayList<>());
        student.registerCourse(c1);

        boolean result = student.registerCourse(c2);
        assertTrue(result);
    }
}
