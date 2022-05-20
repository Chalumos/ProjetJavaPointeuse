package fr.univtours.polytech.timetracker.model.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DepartmentTest {
    @Test
    void Department(){
        Department department = new Department("NameOfDepartment");

        Assertions.assertEquals("NameOfDepartment",department.getName());
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
