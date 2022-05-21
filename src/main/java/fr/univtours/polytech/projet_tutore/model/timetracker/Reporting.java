package fr.univtours.polytech.projet_tutore.model.timetracker;

import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;

/**
 * Class that represents and allows to edit a Reporting.
 */
public class Reporting {
    /** Employee who is reporting */
    private Employee employee;

    /** Date of reporting */
    private Date date;

    /** Time of reporting */
    private Time time;

    /**
     * Comfort constructor allows you to create a reporting by directly initializing all its attributes.
     * @param employee
     * @param date
     * @param time
     */
    public Reporting(Employee employee, Date date, Time time) throws Exception {
        this.employee = employee;
        this.date = date;
        this.time = time;
    }

    /**
     * Get the employee who is reporting.
     * @return The employee who is reporting.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Get the date of the reporting.
     * @return The date of the reporting.
     */
    public Date getDate() throws Exception {
        return date;
    }

    /**
     * Get the time of the reporting.
     * @return The time of the reporting.
     */
    public Time getTime() throws Exception {
        return time;
    }

    /**
     * Set the employee who is reporting.
     * @param employee The new employee.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Set the date of the reporting.
     * @param date The new date.
     */
    public void setDate(Date date) throws Exception {
        this.date = date;
    }

    /**
     * Set the time of the reporting.
     * @param time The new time.
     */
    public void setTime(Time time) throws Exception {
        this.time = time;
    }

    @Override
    public String toString() {

        String message = null;
        try {
            message = "[ " + getEmployee().getFirstName() + " " + getEmployee().getLastName().toUpperCase() + " | " + getDate().toString() + " | " + getTime().toString() + " ]";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return message;
    }
}
