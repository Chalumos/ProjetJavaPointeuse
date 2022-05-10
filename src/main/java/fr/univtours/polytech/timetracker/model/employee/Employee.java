package fr.univtours.polytech.timetracker.model.employee;

import fr.univtours.polytech.timetracker.model.company.Department;

public class Employee {
    private String id;
    private String lastName;
    private String firstName;
    private Department department;
    private EmployeeCard employeeCard;
    //private Schedule schedule;

    public Employee(String id, String lastName, String firstName, Department departement) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.department = departement;
        this.employeeCard = new EmployeeCard(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
