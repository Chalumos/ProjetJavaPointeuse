package fr.univtours.polytech.timetracker.model.date;

import fr.univtours.polytech.timetracker.model.company.Department;
import fr.univtours.polytech.timetracker.model.employee.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class EmployeeTest {
    @Test
    void Employee() {
        LocalDate date = LocalDate.now();
        String id = date.getYear()+""+date.getMonthValue()+""+date.getDayOfMonth() + System.currentTimeMillis();
        Employee employee = new Employee();

        Assertions.assertNotEquals(id, employee.getId());
        Assertions.assertEquals("unknow", employee.getLastName());
        Assertions.assertEquals("unknow", employee.getFirstName());
        Assertions.assertEquals(null, employee.getDepartment());
        Assertions.assertEquals(null, employee.getEmployeeCard());
        Assertions.assertEquals(null, employee.getSchedule());

        Assertions.assertDoesNotThrow(() -> new Employee("Mendes","Theo",new Department("IT")));
    }

    @Test
    void CheckNameFormat(){
        String lastName = "bon";
        String firstName = "jean";
        Employee employee = new Employee(lastName, firstName);

        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();

        Assertions.assertEquals(lastName, employee.getLastName());
        Assertions.assertEquals(firstName, employee.getFirstName());
    }
}
