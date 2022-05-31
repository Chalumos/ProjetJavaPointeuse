package fr.univtours.polytech.projet_tutore.controller.timetracker;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;
import fr.univtours.polytech.projet_tutore.model.timetracker.TimeTracker;

import java.util.ArrayList;


/**
 * Controller to manage the time-tracker view.
 */
public class TimeTrackerController extends Controller {
    /**
     * Time-tracker controlled by the controller.
     */
    private TimeTracker timeTracker;

    /**
     * List of employee controlled by the controller.
     */
    private ArrayList<Employee> employees;

    /**
     * Create the controller.
     */
    public TimeTrackerController() {
        setTimeTracker(new TimeTracker());
    }

    @Override
    public void initialize() {
        updateTime();

        timeTracker.setEmployees(employees);

        String[] messages = {"date", "time", "employees"};
        notifyObservers(messages);
    }

    /**
     * Try to check an employee.
     */
    public void checkEmployee(Employee employee) {
        try {
            ClockingTime clockingTime = new ClockingTime(employee, timeTracker.getCurrentDate(), timeTracker.getCurrentTime());
            System.out.println("Check employee: " + clockingTime);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Update the time of the time-tracker.
     */
    public void updateTime() {
        timeTracker.setCurrentDate(Date.getCurrentDate());
        timeTracker.setCurrentTime(Time.getCurrentTime());

        String[] messages = {"date", "time"};
        super.notifyObservers(messages);
    }

    /**
     * Update the list of employees.
     */
    public void updateEmployees() {
        String[] messages = {"employees"};
        super.notifyObservers(messages);
    }

    /**
     * Get the time-tracker.
     * @return The time-tracker.
     */
    public TimeTracker getTimeTracker() {
        return timeTracker;
    }

    /**
     * Get the list of employees.
     * @return The list of employees.
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * Set the time-tracker.
     * @param newTimeTracker The new time-tracker.
     */
    public void setTimeTracker(TimeTracker newTimeTracker) {
        timeTracker = newTimeTracker;
    }

    /**
     * Set the list of employees.
     * @param newEmployees The new employee list.
     */
    public void setEmployees(ArrayList<Employee> newEmployees) {
        this.employees = newEmployees;
    }
}
