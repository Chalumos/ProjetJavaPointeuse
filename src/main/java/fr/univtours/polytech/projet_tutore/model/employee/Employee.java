package fr.univtours.polytech.projet_tutore.model.employee;

import fr.univtours.polytech.projet_tutore.model.date.Schedule;

import java.time.LocalDate;


/**
 * Employee of a company.
 */
public class Employee {
    /**
     * ID of the employee.
     */
    private String id;

    /**
     * Last name of the employee;
     */
    private String lastName;

    /**
     * First name of the employee.
     */
    private String firstName;

    /**
     * Schedule of the employee.
     */
    private Schedule schedule;

    /**
     * Create an unknown employee.
     */
    public Employee() {
        this("Unknown", "Unknown", null);
    }

    /**
     * Create an employee with a name but without schedule.
     * @param firstName The first name of the employee.
     * @param lastName The last name of the employee.
     */
    public Employee(String firstName, String lastName) {
        this(firstName, lastName, null);
    }

    /**
     * Create an employee with a name and a schedule.
     * @param firstName First name of the employee.
     * @param lastName Last name of the employee.
     * @param schedule Schedule of the employee.
     */
    public Employee(String firstName, String lastName, Schedule schedule) {
        LocalDate date = LocalDate.now();
        id = date.getYear() + "" + date.getMonthValue() + "" + date.getDayOfMonth() + System.currentTimeMillis();
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        this.schedule = schedule;
    }

    /**
     * Get the ID of the employee.
     * @return The ID of the employee.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the first name of the employee.
     * @return The first name of the employee.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name of the employee.
     * @return The last name of the employee.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the first name of the employee.
     * @param firstName The new first name of the employee.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set the last name of the employee.
     * @param lastName The new last name of the employee.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the schedule of the employee.
     * @return The schedule of the employee.
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Set the schedule of the employee.
     * @param schedule The new schedule of the employee.
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * @return The description of the employee.
     */
    @Override
    public String toString() {
        return  firstName + " " + lastName + " (" + id + ")";
    }
}
