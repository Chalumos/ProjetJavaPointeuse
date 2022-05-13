package fr.univtours.polytech.timetracker.model.company;

import fr.univtours.polytech.timetracker.model.employee.Employee;

import java.util.ArrayList;

public class Department {
    /**
     * Department name
     */
    private String name;
    /**
     * List of employee of a department
     */
    private ArrayList<Employee> employees;

    /**
     * Default constructor
     */
    public Department() {
        this.name = "";
        this.employees = new ArrayList<Employee>();
    }

    /**
     * Constructor with department name
     * @param name
     */
    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<Employee>();
    }

    /**
     * @return the name of the department
     */
    public String getName() {
        return name;
    }


    /**
     * this method allows you to change the name of the department
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name of department
     */
    @Override
    public String toString() {
        return "Department: " + this.name;
    }
}
