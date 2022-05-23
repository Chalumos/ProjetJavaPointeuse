package fr.univtours.polytech.projet_tutore.model.data;

import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class EmployeeDataManagerTest {
    private DataManager dataManager;

    @BeforeEach
    void setUp() {
        EmployeeDataManager dataManager = new EmployeeDataManager();
    }

    @Test
    void testEmployeeDataManager() {
        EmployeeDataManager employeeDataManager= new EmployeeDataManager();

        Assertions.assertNotNull(employeeDataManager.getFilePath());
        System.out.println(employeeDataManager.getFilePath());
    }

    @Test
    void testInitialize() {
        EmployeeDataManager employeeDataManager= new EmployeeDataManager();
        employeeDataManager.setFilePath(null);
        Assertions.assertNull(employeeDataManager.getFilePath());

        employeeDataManager.initialize();
        Assertions.assertNotNull(employeeDataManager.getFilePath());

        System.out.println(employeeDataManager.getFilePath());
    }

    @Test
    void testParse() {

    }

    @Test
    void testSerializeEmployees() throws IOException {
        Employee employee= new Employee("Jean","Truc");
        ArrayList<Employee> theList =new ArrayList<Employee>();
        theList.add(employee);
        EmployeeDataManager employeeDataManager= new EmployeeDataManager();


        employeeDataManager.serializeEmployees(theList);
        //TODO tester le parse() !!

    }
}
