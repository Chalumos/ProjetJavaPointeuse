package fr.univtours.polytech.timetracker.model.company;

import java.util.ArrayList;

public class Company {
    /**
     * Company name
     */
    private String name;

    /**
     * List of departments of a company
     */
    private ArrayList<Department> departments;

    /**
     * Default constructor
     */
    public Company() {
        this.name = "";
        this.departments = new ArrayList<Department>();
    }

    /**
     * Constructor with company name
     * @param name
     */
    public Company(String name) {
        this.name = name;
        this.departments = new ArrayList<Department>();
    }

    /**
     * Add a department to the company.
     * @param department New departement of company.
     */
    public void addDepartment(Department department) throws Exception {
        for (Department counterDepartment : departments){
            if (counterDepartment == department){
                throw new Exception();
            }
        }
        this.departments.add(department);
    }
    /**
     * Delete a department of the company.
     * @param department Department to remove.
     */
    public void deleteDepartment(Department department){
        departments.remove(department);
    }

    /**
     * @return the name of the company
     */
    public String getName() {
        return name;
    }

    /**
     * This method allows you to change the name of the company
     * @param name Name of the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name of company
     */
    @Override
    public String toString() {
        return "Company: " + this.name;
    }
 }
