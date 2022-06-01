package fr.univtours.polytech.projet_tutore.model.company;

import fr.univtours.polytech.projet_tutore.model.employee.Employee;

import java.util.ArrayList;

public class Department {
    /**
     * Department name.
     */
    private String name;

    /**
     * List of employees of the department.
     */
    private ArrayList<Employee> employees;

    /**
     * Create an unknown department without employees.
     */
    public Department() {
        setName("Unknown");
        this.employees = new ArrayList<Employee>();
    }

    /**
     * Create a department with a name but without employees.
     * @param name Name of the department.
     */
    public Department(String name) {
        setName(name);
        this.employees = new ArrayList<Employee>();
    }

    /**
     * Add an employee to the department.
     * @param employee New employee to add.
     * @throws Exception If the employee is already in the department.
     */
    public void addEmployee(Employee employee) throws Exception {
        if (!isEmployeeInDepartment(employee)) {
            this.employees.add(employee);
        } else {
            throw new Exception("This employee is already in the department");
        }
    }

    /**
     * Delete an employee to the department.
     * @param employee Employee to remove.
     * @throws Exception If the employee isn't in the department.
     */
    public void deleteEmployee(Employee employee) throws Exception {
        if (isEmployeeInDepartment(employee)) {
            this.employees.remove(employee);
        } else {
            throw new Exception("This employee is npt in the department");
        }
    }

    /**
     * Determine if a specific employee is in the department.
     * @param employee Employee to search.
     * @return If a specific employee is in the department
     */
    public boolean isEmployeeInDepartment(Employee employee) {
        boolean flag = false;

        for (Employee counterEmployee : employees){
            if (counterEmployee == employee){
                flag = true;
            }
        }

        return flag;
    }

    /**
     * Get the name of the department.
     * @return The name of the department
     */
    public String getName() {
        return name;
    }

    /**
     * Get the list of employees of the department.
     * @return List of employees of the department
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * Set the name of the department.
     * @param name New name of the department.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
