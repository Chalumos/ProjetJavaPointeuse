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
     * Add an employee to the department.
     * @param employee New employee to the department.
     */
    public void addEmployee(Employee employee) throws Exception {
        for(Employee counterEmployee : this.employees){
            if (counterEmployee == employee){
                throw new Exception();
            }
        }
        this.employees.add(employee);
    }

    /**
     * Delete an employee to the department.
     * @param employee Employee to remove.
     */
    public void deleteEmployee(Employee employee) {
        this.employees.remove(employee);
    }

    /**
     * Determine if a specific employee is in the department.
     * @param employee Employee to search.
     * @return If a specific employee is in the department
     */
    public boolean isEmployeeInDepartment(Employee employee){
        boolean flag = false;
        for (Employee counterEmployee : employees){
            if (counterEmployee == employee){
                flag = true;
            }
        }
        return flag;
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
     * @return The name of department
     */
    @Override
    public String toString() {
        return "Department: " + this.name;
    }
}
