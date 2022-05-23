package fr.univtours.polytech.projet_tutore.model.employee;

import fr.univtours.polytech.projet_tutore.model.date.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeTest {
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("John", "Doe");
    }

    @Test
    void testEmployee() {
        employee = new Employee();
        Assertions.assertEquals("Unknown", employee.getFirstName());
        Assertions.assertEquals("Unknown", employee.getLastName());
        Assertions.assertNull(employee.getSchedule());

        employee = new Employee("John", "Doe");

        Assertions.assertEquals("John", employee.getFirstName());
        Assertions.assertEquals("Doe", employee.getLastName());
        Assertions.assertNull(employee.getSchedule());

        Schedule schedule = new Schedule();
        employee = new Employee("John", "Doe", schedule);

        Assertions.assertEquals("John", employee.getFirstName());
        Assertions.assertEquals("Doe", employee.getLastName());
        Assertions.assertEquals(schedule, employee.getSchedule());
    }

    @Test
    void testSetFirstName() {
        employee.setFirstName("Jane");

        Assertions.assertEquals("Jane", employee.getFirstName());
    }

    @Test
    void testSetLastName() {
        employee.setLastName("Smith");

        Assertions.assertEquals("Smith", employee.getLastName());
    }

    @Test
    void testSetSchedule() {
        Schedule schedule = new Schedule();
        Assertions.assertNull(employee.getSchedule());

        employee.setSchedule(schedule);
        Assertions.assertNotNull(employee.getSchedule());
    }

    @Test
    void testToString() {
        Assertions.assertEquals(employee.toString(), "John Doe (" + employee.getId() + ")");
    }
}
