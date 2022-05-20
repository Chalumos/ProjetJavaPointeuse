package fr.univtours.polytech.timetracker.model.company;

import fr.univtours.polytech.timetracker.model.employee.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CompanyTest {
    @Test
    void CompanyTest() {
        Company company = new Company("NameOfCompany");

        Assertions.assertEquals("NameOfCompany", company.getName());
    }

    @Test
    void addDepartment() throws Exception {
        Company company = new Company();
        company.setName("theCompany");

        Department department = new Department("Department1");
        company.addDepartment(department);

        Assertions.assertEquals(1, company.getDepartments().size());
        Assertions.assertEquals(department, company.getDepartments().get(company.getDepartments().size() - 1));
    }

    @Test
    void deleteDepartment() throws Exception {
        Company company = new Company();
        company.setName("theCompany");

        Department department1 = new Department("Department1");
        Department department2 = new Department("Department2");
        company.addDepartment(department1);
        company.addDepartment(department2);

        company.deleteDepartment(department2);
        Assertions.assertEquals(1,company.getDepartments().size());
        Assertions.assertEquals(department1, company.getDepartments().get(company.getDepartments().size() - 1) );
    }

    @Test
    void isDepartmentInCompany() throws Exception {
        Company company = new Company("theCompany");

        Department department1 = new Department("department1");
        Department department2 = new Department("department2");
        Department department3 = new Department("department3");
        company.addDepartment(department1);
        company.addDepartment(department2);

        Assertions.assertEquals(true, company.isDepartmentInCompany(department1));
        Assertions.assertEquals(false, company.isDepartmentInCompany(department3));
    }

    @Test
    void setName(){
        Company company = new Company();
        company.setName("theCompany");

        Assertions.assertEquals("theCompany",company.getName());
    }

    @Test
    void toStr(){
        Company company = new Company("theCompany");

        Assertions.assertEquals("Company: theCompany",company.toString());

    }

}