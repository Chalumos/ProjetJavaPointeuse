package fr.univtours.polytech.projet_tutore.model.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CompanyTest {
    @Test
    void constructors() {
        Company company = new Company();

        Assertions.assertEquals("Unknown", company.getName());
        Assertions.assertEquals(0, company.getDepartments().size());

        company = new Company("NameOfCompany");

        Assertions.assertEquals("NameOfCompany", company.getName());
        Assertions.assertEquals(0, company.getDepartments().size());
    }

    @Test
    void addDepartment() {
        try {
            Company company = new Company();

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
    void deleteDepartment() {
        try {
            Company company = new Company();

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
    void isDepartmentInCompany() {
        try {
            Company company = new Company();

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
    void setName(){
        Company company = new Company();
        company.setName("theCompany");

        Assertions.assertEquals("theCompany",company.getName());
    }

    @Test
    void toStr() {
        Company company = new Company();
        Assertions.assertEquals("Company: Unknown", company.toString());

        company = new Company("theCompany");
        Assertions.assertEquals("Company: theCompany", company.toString());
    }
}