package fr.univtours.polytech.projet_tutore.model.data;

import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeDataManagerTest {
    private EmployeeDataManager employeeDataManager;

    @BeforeEach
    void setUp() {
        employeeDataManager = new EmployeeDataManager();
    }

    @Test
    void testEmployeeDataManager() {
        Assertions.assertNotNull(employeeDataManager.getFilePath());
    }

    @Test
    void testInitialize() {
        employeeDataManager.setFilePath(null);
        Assertions.assertNull(employeeDataManager.getFilePath());

        employeeDataManager.initialize();
        Assertions.assertNotNull(employeeDataManager.getFilePath());
    }

    @Test
    void testSerializeEmployees() {
        ArrayList<Employee> theList =new ArrayList<Employee>();

        Employee employee1= new Employee("Jean","Truc");
        theList.add(employee1);

        Employee employee2=new Employee("Jean","mi");
        theList.add(employee2);

        ArrayList<Employee> theNewList = new ArrayList<Employee>();

        try{
            String path = (new File(employeeDataManager.getFilePath())).getParentFile() + File.separator + "EmployeeTest.txt";
            employeeDataManager.setFilePath(path);
            employeeDataManager.serializeEmployees(theList);
            theNewList.addAll(employeeDataManager.parseEmployees());

            Assertions.assertEquals(employee1.toString(),theNewList.get(0).toString());
            Assertions.assertEquals(employee2.toString(),theNewList.get(1).toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testSerializeEmployees_emptyFile(){
        ArrayList<Employee> theNewList = new ArrayList<Employee>();

        String path = (new File(employeeDataManager.getFilePath())).getParentFile() + File.separator + "emptyFileTest.txt";
        employeeDataManager.setFilePath(path);


        try{
            theNewList.addAll(employeeDataManager.parseEmployees());
            Assertions.assertEquals(new ArrayList<Employee>(1),theNewList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testSerializeEmployees_ThrowsExceptions(){
        employeeDataManager.setFilePath("");

        Assertions.assertThrows(FileNotFoundException.class,()->employeeDataManager.parseEmployees());
    }
}
