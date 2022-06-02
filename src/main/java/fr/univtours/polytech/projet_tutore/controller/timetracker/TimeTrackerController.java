package fr.univtours.polytech.projet_tutore.controller.timetracker;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.Stub;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.socket.Client;
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
     * Clocking-times controlled which are not send by the controller.
     */
    private ArrayList<ClockingTime> clockingTimes;

    /**
     * Create the controller.
     */
    public TimeTrackerController() {
        setTimeTracker(null);
    }

    @Override
    public void initialize() {
        Company company = Stub.generateCompany();
        TimeTracker timeTracker = new TimeTracker();
        ArrayList<Employee> employees = new ArrayList<>();

        // Get the employees.
        for(Department department : company.getDepartments()){
            for (Employee employee : department.getEmployees()){
                employees.add(employee);
            }
        }

        timeTracker.setEmployees(employees);
        setTimeTracker(timeTracker);

        setClockingTimes(Stub.getClockingTimeList());

        updateTime();

        String[] messages = {"date", "time", "employees", "clockingTime"};
        notifyObservers(messages);
    }

    /**
     * Try to check an employee.
     */
    public void checkEmployee(Employee employee) {
        try {
            ClockingTime clockingTime = new ClockingTime(employee, timeTracker.getCurrentDate(), timeTracker.getCurrentTime());

            // Add clocking times to a list because if the connection failed,
            // the list of clocking time is saved anyway.
            clockingTimes.add(clockingTime);
            new Client(clockingTimes);
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
     * Get the Clocking-time.
     * @return The clocking-time.
     */
    public ArrayList<ClockingTime> getClockingTimes() {
        return clockingTimes;
    }

    /**
     * Set the time-tracker.
     * @param newTimeTracker The new time-tracker.
     */
    public void setTimeTracker(TimeTracker newTimeTracker) {
        timeTracker = newTimeTracker;
    }

    /**
     * Set the clocking-time.
     * @param newClockingTimes The new clocking-time.
     */
    public void setClockingTimes(ArrayList<ClockingTime> newClockingTimes) {
        this.clockingTimes = newClockingTimes;
    }
}
