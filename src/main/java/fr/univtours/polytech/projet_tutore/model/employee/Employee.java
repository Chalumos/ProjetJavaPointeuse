package fr.univtours.polytech.projet_tutore.model.employee;

import fr.univtours.polytech.projet_tutore.model.date.Schedule;
import fr.univtours.polytech.projet_tutore.model.date.Time;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.UUID;


/**
 * Employee of a company.
 */
public class Employee implements Externalizable {
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
     * overtime of the employee
     */
    private int overtime;

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
        generateID();

        setFirstName(firstName);
        setLastName(lastName);
        this.schedule = schedule;
        overtime=0;
    }

    /**
     * Generate a unique ID for an employee.
     */
    private void generateID() {
        id = UUID.randomUUID().toString();
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
     * Get the schedule of the employee.
     * @return The schedule of the employee.
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Set the first name of the employee.
     * @param firstName The new first name of the employee.
     */
    public void setFirstName(String firstName) {
        if (firstName != null) {
            String firstLetter = firstName.length() > 0 ? firstName.substring(0, 1).toUpperCase() : "";
            String otherLetters = firstName.length() > 1 ? firstName.substring(1).toLowerCase() : "";
            this.firstName = firstLetter + otherLetters;
        } else {
            this.firstName = null;
        }
    }

    /**
     * Set the last name of the employee.
     * @param lastName The new last name of the employee.
     */
    public void setLastName(String lastName) {
        if (lastName != null) {
            String firstLetter = lastName.length() > 0 ? lastName.substring(0, 1).toUpperCase() : "";
            String otherLetters = lastName.length() > 1 ? lastName.substring(1).toLowerCase() : "";
            this.lastName = firstLetter + otherLetters;
        } else {
            this.lastName = null;
        }
    }

    /**
     * Set the schedule of the employee.
     * @param schedule The new schedule of the employee.
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Get overtime of the employee
     * @return the overtime of the employee
     */
    public int getOvertime() {
        return overtime;
    }

    /**
     * set overtime of the employee
     * @param newOvertime the new overtime of the employee
     */
    public void setOvertime(int newOvertime){
        overtime=newOvertime;
    }

    /**
     * increase the overtime
     * @param timeToAdd the time which is added to the overtime
     */
    public void addOvertime(int timeToAdd){
        overtime+=timeToAdd;
    }

    @Override
    public String toString() {
        return  firstName + " " + lastName;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(firstName);
        out.writeObject(lastName);
        out.writeObject(schedule);
        out.writeObject(overtime);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
        firstName = (String) in.readObject();
        lastName = (String) in.readObject();
        schedule = (Schedule) in.readObject();
        overtime = (int) in.readObject();
    }
}
