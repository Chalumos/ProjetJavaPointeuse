package fr.univtours.polytech.projet_tutore.controller.timetracker;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.Stub;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.data_manager.ClockingTimeDataManager;
import fr.univtours.polytech.projet_tutore.model.data_manager.NetworkSettingsDataManager;
import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.settings.NetworkSettings;
import fr.univtours.polytech.projet_tutore.model.socket.Client;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;
import fr.univtours.polytech.projet_tutore.model.timetracker.TimeTracker;

import java.io.File;
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
     * @return Whether the operation succeed or failed.
     */
    public boolean checkEmployee(Employee employee) {
        boolean isSuccess = true;

        try {
            ClockingTime clockingTime = new ClockingTime(employee, timeTracker.getCurrentDate(), timeTracker.getCurrentTime());

            // Add clocking times to a list because if the connection failed,
            // the list of clocking time is saved anyway.
            clockingTimes.add(clockingTime);

            // Parse(read) the file
            ClockingTimeDataManager clockingTimeDataManager = new ClockingTimeDataManager();
            // Get the project root path.
            File root = new File("");

            // Create the path for the packages.
            String packages = File.separator + "src" +
                    File.separator + "main" +
                    File.separator + "resources" +
                    File.separator + "fr" +
                    File.separator + "univtours" +
                    File.separator + "polytech" +
                    File.separator + "projet_tutore" +
                    File.separator + "data" +
                    File.separator;

            String path = root.getAbsolutePath() + packages + "ClockingTimeTimeTracker.txt";
            clockingTimeDataManager.setFilePath(path);

            ArrayList<ClockingTime> fileClockingTimes = clockingTimeDataManager.parse();

            // List composed of the current clocking-time and all the clocking-times in the file.
            clockingTimes.addAll(fileClockingTimes);

            // Try to send the list of clocking-time to the application
            // new Client(clockingTimes);

            NetworkSettingsDataManager networkSettingsDataManager = new NetworkSettingsDataManager();
            ArrayList<NetworkSettings> networkSettings = networkSettingsDataManager.parse();
            if (networkSettings.size() <= 0 ){
                networkSettings = new ArrayList<>();
                networkSettings.add(new NetworkSettings());
                networkSettingsDataManager.serialize(networkSettings);
                networkSettings = networkSettingsDataManager.parse();
            }
            NetworkSettings networkSetting =  networkSettings.get(0);

            // Success of the sending.
            if (Client.sendData(clockingTimes, networkSetting)) {
                // Clear the file.
                clockingTimeDataManager.serialize(new ArrayList<>());
            }
            // If the sending failed, we stock the no sent clocking times.
            else {
                clockingTimeDataManager.serialize(clockingTimes);
            }

            clockingTimes.clear();
        } catch (Exception exception) {
            exception.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
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
