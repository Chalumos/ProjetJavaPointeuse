package fr.univtours.polytech.timetracker.model.company;

import fr.univtours.polytech.timetracker.model.employee.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DepartmentTest {
    @Test
    void DepartmentTest() {
        Department department = new Department("NameOfDepartment");

        Assertions.assertEquals("NameOfDepartment",department.getName());
    }

    @Test
    void addEmployee() throws Exception {
        Department department = new Department();
        department.setName("theDepartment");

        Employee employee = new Employee("Pierre","Nicolas");
        department.addEmployee(employee);

        Assertions.assertEquals(1,department.getEmployees().size());
        Assertions.assertEquals(employee, department.getEmployees().get(department.getEmployees().size() - 1) );
    }

    @Test
    void deleteEmployee() throws Exception {
        Department department = new Department();
        department.setName("theDepartment");

        Employee employee1 = new Employee("Pierre","Nicolas");
        Employee employee2 = new Employee("James","Stephen");
        department.addEmployee(employee1);
        department.addEmployee(employee2);

        department.deleteEmployee(employee2);
        Assertions.assertEquals(1,department.getEmployees().size());
        Assertions.assertEquals(employee1, department.getEmployees().get(department.getEmployees().size() - 1) );
    }

    @Test
    void isEmployeeInDepartment() throws Exception {
        Department department = new Department("theDepartment");

        Employee employee1 = new Employee("Pierre","Nicolas");
        Employee employee2 = new Employee("James","Stephen");
        Employee employee3 = new Employee("Joe","John");
        department.addEmployee(employee1);
        department.addEmployee(employee2);

        Assertions.assertEquals(true, department.isEmployeeInDepartment(employee1));
        Assertions.assertEquals(false, department.isEmployeeInDepartment(employee3));
    }

    @Test
    void setName(){
        Department department=new Department();
        department.setName("theDepartment");

        Assertions.assertEquals("theDepartment",department.getName());
    }

    @Test
    void toStr(){
        Department department=new Department("theDepartment");

        Assertions.assertEquals("Department: theDepartment",department.toString());

    }
}
