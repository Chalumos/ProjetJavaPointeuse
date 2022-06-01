package fr.univtours.polytech.projet_tutore.model.company;

import fr.univtours.polytech.projet_tutore.model.employee.Employee;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Company implements Externalizable {
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
     * Get the list of employee of the company.
     * @return The list of employee of the company.
     */
    public ArrayList<Employee> getEmployees(){
        ArrayList<Employee> employees = new ArrayList<Employee>();
        for (Department department : departments) {
            employees.addAll(department.getEmployees());
        }

        return employees;
    }

    /**
     * Get the department of the employee.
     * @param employee The employee for which search the department.
     * @return The department of the employee.
     */
    public Department getDepartment(Employee employee) {
        Department employeeDepartment = null;

        for (Department department : departments) {
            List<Employee> employeesFilter = department.getEmployees().stream().filter(employeeI -> employeeI == employee).toList();
            if (!employeesFilter.isEmpty()) {
                employeeDepartment = department;
            }
        }

        return employeeDepartment;
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
        return name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(departments);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        departments =(ArrayList<Department>) in.readObject();
    }
 }
