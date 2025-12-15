package org.Harel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    Department dept = new Department("Math");

    @Test
    @DisplayName("Weights should total 100 when valid")
    void testAssignmentWeightValid() {
        Course c = new Course("Discrete", 3.0, dept);

        c.addAssignment("A1", 50, 100);
        c.addAssignment("A2", 50, 100);

        assertTrue(c.isAssignmentWeightValid());
    }
    @Test
    @DisplayName("Student registration should succeed")
    void testRegisterStudent() {
        Course c = new Course("Discrete", 3.0, dept);
        Student student = new Student("Benji", Student.Gender.MALE,
                new Address(10, "nack", "Montreal", Address.Province.ON, "A1B2AC3"),
                dept);

        c.addAssignment("K1", 100, 100);

        boolean result = c.registerStudent(student);
        assertTrue(result);
        assertTrue(c.getRegisteredStudents().contains(student));
        assertEquals(1, c.getAssignment().get(0).getScores().size());
        assertNull(c.getAssignment().get(0).getScores().get(0));
        assertEquals(1, c.getFinalScores().size());
        assertNull(c.getFinalScores().get(0));
    }

    @Test
    @DisplayName("Average calculation should be correct")
    void testCalcStudentsAverage() {
        Course c = new Course("Discrete", 3.0, dept);
        c.addAssignment("A1", 50, 100);
        c.addAssignment("A2", 50, 100);

        Student s1 = new Student("Benji", Student.Gender.MALE,
                new Address(10, "King", "Montreal", Address.Province.ON, "A1A1A1"),
                dept);

        c.registerStudent(s1);

        // Set scores manually
        c.getAssignment().get(0).getScores().set(0, 80); // 80 * 0.5 = 40
        c.getAssignment().get(1).getScores().set(0, 90); // 90 * 0.5 = 45

        int[] avg = c.calcStudentsAverage();

        assertEquals(85, avg[0]); // 40 + 45 = 85
    }
    @Test
    @DisplayName("Adding an assignment should work")
    void testAddAssignment() {
        Course c = new Course("Discrete", 3.0, dept);

        boolean result = c.addAssignment("A1", 100, 100);

        assertTrue(result);
        assertEquals(1, c.getAssignment().size());
        assertEquals("A1", c.getAssignment().get(0).getAssignmentName());
    }

    @Test
    @DisplayName("Random score generation should fill lists")
    void testGenerateScores() {
        Course c = new Course("Discrete", 3.0, dept);
        c.addAssignment("A1", 100, 100);

        Student s1 = new Student("Benji", Student.Gender.MALE,
                new Address(10, "King", "Montreal", Address.Province.ON, "A1A1A1"),
                dept);

        c.registerStudent(s1);

        c.generateScores();

        Integer score = c.getAssignment().get(0).getScores().get(0);
        assertNotNull(score);
        assertTrue(score >= 0 && score <= 100);
        assertEquals(1, c.getFinalScores().size());
    }

    @Test
    @DisplayName("displayScores() should run without errors")
    void testDisplayScores() {
        Course c = new Course("Discrete", 3.0, dept);
        c.addAssignment("A1", 100, 100);

        Student s1 = new Student("Benji", Student.Gender.MALE,
                new Address(10, "King", "Montreal", Address.Province.ON, "A1A1A1"),
                dept);

        c.registerStudent(s1);
        c.generateScores();

        assertDoesNotThrow(c::displayScores);
    }
}