package fr.univtours.polytech.projet_tutore.model.timetracker;

import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;

import java.util.ArrayList;

public class TimeTracker {
    /**
     * The current date.
     */
    private Date currentDate;

    /**
     * The currentTime;
     */
    private Time currentTime;

    /**
     * Employees of the company.
     */
    private final ArrayList<Employee> employees;

    /**
     * Create a time tracker without any employee.
     */
    public TimeTracker() {
        setCurrentDate(Date.getCurrentDate());
        setCurrentTime(Time.getCurrentTime());
        employees = new ArrayList<Employee>();
    }

    /**
     * Get the current date.
     * @return The current date;
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * Get the current time.
     * @return The current time;
     */
    public Time getCurrentTime() {
        return currentTime;
    }

    /**
     * Get the employees of the company.
     * @return The employees.
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * Set the current date of the time-tracker.
     * @param newCurrentDate The new current date.
     */
    public void setCurrentDate(Date newCurrentDate) {
        currentDate = newCurrentDate;
    }

    /**
     * Set the current time of the time-tracker.
     * @param newCurrentTime The new current time.
     */
    public void setCurrentTime(Time newCurrentTime) {
        currentTime = newCurrentTime;
    }

    /**
     * Set the list of employees.
     * @param newEmployeeList The new list of employees.
     */
    public void setEmployees(ArrayList<Employee> newEmployeeList) {
        employees.clear();
        employees.addAll(newEmployeeList);
    }

    @Override
    public String toString() {
        String date = getCurrentDate().toString();
        String time = getCurrentTime().toString();
        String employees = String.valueOf(getEmployees().size());
        return "Time-tracker (" + date + " at " + time + ", " + employees + " employees)";
    }
}
