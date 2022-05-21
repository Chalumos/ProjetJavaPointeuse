package fr.univtours.polytech.timetracker.model.company;

import fr.univtours.polytech.timetracker.model.employee.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DepartmentTest {
    @Test
    void constructors() {
        Department department = new Department();

        Assertions.assertEquals("Unknown", department.getName());
        Assertions.assertEquals(0, department.getEmployees().size());

        department = new Department("NameOfDepartment");

        Assertions.assertEquals("NameOfDepartment", department.getName());
        Assertions.assertEquals(0, department.getEmployees().size());
    }

    @Test
    void addEmployee() throws Exception {
        try {
            Department department = new Department();

            Employee employee = new Employee("John", "Doe");
            department.addEmployee(employee);

            Assertions.assertEquals(1, department.getEmployees().size());
            Assertions.assertEquals(employee, department.getEmployees().get(0));
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void deleteEmployee() throws Exception {
        try {
            Department department = new Department();

            Employee employee1 = new Employee("John", "Doe");
            Employee employee2 = new Employee("Jane", "Doe");
            department.addEmployee(employee1);
            department.addEmployee(employee2);

            Assertions.assertEquals(2, department.getEmployees().size());

            department.deleteEmployee(employee2);
            Assertions.assertEquals(1, department.getEmployees().size());
            Assertions.assertEquals(employee1, department.getEmployees().get(0));
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void isEmployeeInDepartment() throws Exception {
        try {
            Department department = new Department();

            Employee employee1 = new Employee("John", "Doe");
            Employee employee2 = new Employee("Jane", "Doe");
            Employee employee3 = new Employee("John", "Smith");
            department.addEmployee(employee1);
            department.addEmployee(employee2);

            Assertions.assertTrue(department.isEmployeeInDepartment(employee1));
            Assertions.assertTrue(department.isEmployeeInDepartment(employee2));
            Assertions.assertFalse(department.isEmployeeInDepartment(employee3));
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void setName() {
        Department department = new Department();
        department.setName("theDepartment");

        Assertions.assertEquals("theDepartment", department.getName());
    }

    @Test
    void toStr() {
        Department department = new Department();
        Assertions.assertEquals("Department: Unknown",department.toString());

        department = new Department("theDepartment");
        Assertions.assertEquals("Department: theDepartment",department.toString());
    }
}
