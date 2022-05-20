package fr.univtours.polytech.timetracker.model.employee;

import fr.univtours.polytech.timetracker.model.company.Department;
import fr.univtours.polytech.timetracker.model.date.Date;
import fr.univtours.polytech.timetracker.model.date.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

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
     * Employee card
     */
    private EmployeeCard employeeCard;
    /**
     * Employee Schedule
     */
    private Schedule schedule;

    public Employee(){
        LocalDate date = LocalDate.now();
        id = date.getYear()+""+date.getMonthValue()+""+date.getDayOfMonth() + System.currentTimeMillis();
        lastName = "Unknow";
        firstName = "Unknow";
        employeeCard = null;
        schedule = null;
    }

    public Employee(String lastName, String firstName) {
        this(lastName, firstName, null);
    }

    /**
     * Comfort constructor allows you to create an employee by directly initializing all its attributes
     * @param lastName
     * @param firstName
     * @param department
     */
    public Employee(String lastName, String firstName, Department department) {
        LocalDate date = LocalDate.now();
        id = date.getYear()+""+date.getMonthValue()+""+date.getDayOfMonth() + System.currentTimeMillis();
        this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        this.schedule = new Schedule();
        this.employeeCard = new EmployeeCard(this);
    }

    /**
     * Get the employee id
     * @return id of employee
     */
    public String getId() {
        return id;
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
        return "Employee : "+ lastName + " " + firstName + " ID: "+ id;
    }
}
