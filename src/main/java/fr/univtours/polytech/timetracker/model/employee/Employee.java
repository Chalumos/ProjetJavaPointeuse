package fr.univtours.polytech.timetracker.model.employee;

import fr.univtours.polytech.timetracker.model.company.Department;
import fr.univtours.polytech.timetracker.model.date.Schedule;

public class Employee {

    /**
     * Employee id
     */
    private String id;

    /**
     * Employee last name
     */
    private String lastName;
    /**
     * Employee first name
     */
    private String firstName;
    /**
     * Employee department
     */
    private Department department;
    /**
     * Employee card
     */
    private EmployeeCard employeeCard;
    /**
     * Employee Schedule
     */
    private Schedule schedule;

    /**
     * Comfort constructor allows you to create an employee by directly initializing all its attributes
     * @param id
     * @param lastName
     * @param firstName
     * @param department
     */
    public Employee(String id, String lastName, String firstName, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.department = department;
        this.employeeCard = new EmployeeCard(this);
        this.schedule = new Schedule();
    }

    /**
     * Get the employee id
     * @return id of employee
     */
    public String getId() {
        return id;
    }


    /**
     * Set the employee id
     * @param id the new employee id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the employee last name
     * @return last name of employee
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the employee lastName
     * @param lastName the new employee lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the employee first name
     * @return first name of employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the employee firstName
     * @param firstName the new employee firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the employee department
     * @return department of employee
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Set the employee department
     * @param department the new employee department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Get the employee Card
     * @return the EmployeeCard
     */
    public EmployeeCard getEmployeeCard() {
        return employeeCard;
    }

    /**
     * Set the employee card
     * @param employeeCard the new employee card
     */
    public void setEmployeeCard(EmployeeCard employeeCard) {
        this.employeeCard = employeeCard;
    }

    /**
     * Get the employee schedule
     * @return the Schedule of employee
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Set the employee schedule
     * @param schedule the new employee schedule
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * @return Employee informations
     */
    @Override
    public String toString() {
        return "Employee : "+ lastName + " " + firstName + " ID: "+ id +
                department.toString();
    }
}
