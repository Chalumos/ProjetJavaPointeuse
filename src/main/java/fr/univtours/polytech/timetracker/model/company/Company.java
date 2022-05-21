package fr.univtours.polytech.timetracker.model.company;

import java.util.ArrayList;

public class Company {
    /**
     * Company name.
     */
    private String name;

    /**
     * List of departments of the company.
     */
    private ArrayList<Department> departments;

    /**
     * Create an unknown company without departments.
     */
    public Company() {
        setName("Unknown");
        this.departments = new ArrayList<Department>();
    }

    /**
     * Create a company with a name but without departments.
     * @param name Name of the company.
     */
    public Company(String name) {
        setName(name);
        this.departments = new ArrayList<Department>();
    }

    /**
     * Add a department to the company.
     * @param department New department to add.
     * @throws Exception If the department is already in the company.
     */
    public void addDepartment(Department department) throws Exception {
        if (!isDepartmentInCompany(department)) {
            departments.add(department);
        } else {
            throw new Exception("This department is already in the company.");
        }
    }

    /**
     * Delete a department from the company.
     * @param department Department to remove.
     * @throws Exception If the department isn't in the company.
     */
    public void deleteDepartment(Department department) throws Exception {
        if (isDepartmentInCompany(department)) {
            departments.remove(department);
        } else {
            throw new Exception("This department is not in the company.");
        }
    }

    /**
     * Determine whether a specific department is in the company or not.
     * @param department Department to search.
     * @return If a specific department is in the company.
     */
    public boolean isDepartmentInCompany(Department department){
        boolean flag = false;

        for (Department counterDepartment : getDepartments()){
            if (counterDepartment == department) {
                flag = true;
            }
        }

        return flag;
    }

    /**
     * Get the name of the company.
     * @return The name of the company.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the list of departments of the company.
     * @return List of departments of the company.
     */
    public ArrayList<Department> getDepartments() {
        return departments;
    }

    /**
     * Set the name of the company.
     * @param name New name of the company.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company: " + this.name;
    }
 }
