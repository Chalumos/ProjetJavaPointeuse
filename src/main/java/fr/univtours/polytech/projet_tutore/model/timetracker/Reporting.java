package fr.univtours.polytech.projet_tutore.model.timetracker;

import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;

/**
 * Class that represents and allows to edit a Reporting.
 */
public class Reporting {
    /**
     * Employee who is reporting
     */
    private Employee employee;

    /**
     * Date of reporting
     */
    private Date date;

    /**
     * Time of reporting
     */
    private Time time;

    /**
     * Create an empty reporting.
     */
    public Reporting() {
        this(null, null, null);
    }

    /**
     * Create a reporting with arguments.
     * @param employee The employee of the reporting.
     * @param date The date of the reporting.
     * @param time The time of the reporting.
     */
    public Reporting(Employee employee, Date date, Time time) {
        setEmployee(employee);
        setDate(date);
        setTime(time);
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
    public Date getDate() {
        return date;
    }

    /**
     * Get the time of the reporting.
     * @return The time of the reporting.
     */
    public Time getTime() {
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
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Set the time of the reporting.
     * @param time The new time.
     */
    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        String employee = getEmployee() != null
                ? getEmployee().getFirstName() + " " + getEmployee().getLastName().toUpperCase()
                : "Unknown";
        String date = getDate() != null ? getDate().toString() : "Unknown";
        String time = getTime() != null ? getTime().toString() : "Unknown";

        return "[ " + employee + " | " + date + " | " + time + " ]";
    }
}
