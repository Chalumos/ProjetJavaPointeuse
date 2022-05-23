package fr.univtours.polytech.projet_tutore.model.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompanyTest {
    private Company company;

    @BeforeEach
    void setUp() {
        company = new Company();
    }

    @Test
    void testCompany() {
        Assertions.assertEquals("Unknown", company.getName());
        Assertions.assertEquals(0, company.getDepartments().size());

        company = new Company("NameOfCompany");

        Assertions.assertEquals("NameOfCompany", company.getName());
        Assertions.assertEquals(0, company.getDepartments().size());
    }

    @Test
    void testAddDepartment() {
        try {
            Department department = new Department("Department 1");
            company.addDepartment(department);

            Assertions.assertEquals(1, company.getDepartments().size());
            Assertions.assertEquals(department, company.getDepartments().get(0));
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testDeleteDepartment() {
        try {
            Department department1 = new Department("Department 1");
            Department department2 = new Department("Department 2");
            company.addDepartment(department1);
            company.addDepartment(department2);

            Assertions.assertEquals(2, company.getDepartments().size());

            company.deleteDepartment(department2);
            Assertions.assertEquals(1, company.getDepartments().size());
            Assertions.assertEquals(department1, company.getDepartments().get(0));
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testIsDepartmentInCompany() {
        try {
            Department department1 = new Department("Department 1");
            Department department2 = new Department("Department 2");
            Department department3 = new Department("Department 3");
            company.addDepartment(department1);
            company.addDepartment(department2);

            Assertions.assertTrue(company.isDepartmentInCompany(department1));
            Assertions.assertTrue(company.isDepartmentInCompany(department2));
            Assertions.assertFalse(company.isDepartmentInCompany(department3));
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testSetName(){
        company.setName("theCompany");

        Assertions.assertEquals("theCompany",company.getName());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("Company: Unknown", company.toString());

        company = new Company("theCompany");
        Assertions.assertEquals("Company: theCompany", company.toString());
    }
}