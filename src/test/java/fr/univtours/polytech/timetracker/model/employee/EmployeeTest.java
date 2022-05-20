package fr.univtours.polytech.timetracker.model.employee;

import fr.univtours.polytech.timetracker.model.date.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeTest {
    @Test
    void EmployeeTest() {
        Employee employee = new Employee();
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
    void setFirstName() {
        Employee employee = new Employee("John", "Doe");
        employee.setFirstName("Jane");

        Assertions.assertEquals("Jane", employee.getFirstName());
    }

    @Test
    void setLastName() {
        Employee employee = new Employee("John", "Doe");
        employee.setLastName("Smith");

        Assertions.assertEquals("Smith", employee.getLastName());
    }

    @Test
    void setSchedule() {
        Employee employee = new Employee("John", "Doe");
        Schedule schedule = new Schedule();
        Assertions.assertNull(employee.getSchedule());

        employee.setSchedule(schedule);
        Assertions.assertNotNull(employee.getSchedule());
    }

    @Test
    void toStr() {
        Employee employee = new Employee("John", "Doe");
        Assertions.assertEquals(employee.toString(), "John Doe (" + employee.getId() + ")");
    }
}
