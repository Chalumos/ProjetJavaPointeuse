package fr.univtours.polytech.projet_tutore.controller.timetracker;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.Reporting;

import java.util.ArrayList;


/**
 * Controller to manage the time-tracker view.
 */
public class TimeTrackerController extends Controller {
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
    private ArrayList<Employee> employees = new ArrayList<Employee>();

    /**
     * Create the controller.
     */
    public TimeTrackerController() {}

    @Override
    public void initialize() {
        updateTime();
        employees.add(new Employee("John", "Doe"));
        employees.add(new Employee("Jane", "Doe"));

        String[] messages = {"date", "time", "employees"};
        notifyObservers(messages);
    }

    /**
     * Try to check an employee.
     */
    public void checkEmployee(Employee employee) {
        try {
            Reporting reporting = new Reporting(employee, getCurrentDate(), getCurrentTime());
            System.out.println("Check employee: " + reporting);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
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
     * Update the time of the time-tracker.
     */
    public void updateTime() {
        currentDate = Date.getCurrentDate();
        currentTime = Time.getCurrentTime();

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
}
