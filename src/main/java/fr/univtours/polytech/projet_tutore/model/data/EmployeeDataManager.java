package fr.univtours.polytech.projet_tutore.model.data;

import fr.univtours.polytech.projet_tutore.model.employee.Employee;

import java.io.*;
import java.util.ArrayList;

public class EmployeeDataManager extends DataManager {

    public EmployeeDataManager(){
        super();
    }

    @Override
    public void initialize() {
        // Get the project root path.
        File root = new File("");

        // Create the path for the packages.
        String packages = File.separator + "src" +
                File.separator + "main" +
                File.separator + "resources" +
                File.separator + "fr" +
                File.separator + "univtours" +
                File.separator + "polytech" +
                File.separator + "projet_tutore" +
                File.separator + "data" +
                File.separator;


        String path = root.getAbsolutePath() + packages + "Employee.txt";

        setFilePath(path);
    }

    public void serializeEmployees(ArrayList<Employee> employees) throws IOException {
        super.serialize(employees);
    }

    public ArrayList<Employee> parseEmployees() throws IOException, ClassNotFoundException {
        return super.parse();
    }
}
