package fr.univtours.polytech.projet_tutore.controller.application;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.Stub;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.data_manager.ClockingTimeDataManager;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.socket.ServerMultiThread;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;

import java.awt.*;
import java.util.ArrayList;


/**
 * Controller to manage the application view.
 */
public class ApplicationController extends Controller {
    /**
     * Company that the application is monitoring.
     */
    private Company company;

    /**
     * Clocking times of the employees of the company.
     */
    private ArrayList<ClockingTime> clockingTimes;

    /**
     * The selected employee.
     */
    private Employee selectedEmployee;

    /**
     * The server of the application.
     */
    private ServerMultiThread serverMultiThread;

    /**
     * Create the company.
     */
    public ApplicationController() {
        setCompany(null);
    }

    @Override
    public void initialize() {
        serverMultiThread = new ServerMultiThread((clockingTimes) -> {
            getClockingTimes().addAll((ArrayList<ClockingTime>) clockingTimes);
            String[] messages = {"clocking_times"};
            notifyObservers(messages);
            return null;
        });

        serverMultiThread.start();
        do {
            try {
                setCompany(Stub.generateCompany());
                setClockingTimes(Stub.getClockingTimeList());

                String[] messages = {"employee_filter", "department_filter", "clocking_times", "employees"};
                notifyObservers(messages);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } while (getCompany() == null);
    }

    /**
     * Remove a clocking time.
     */
    public void removeClockingTime(ClockingTime clockingTime) {
        ArrayList<ClockingTime> clockingTimes = getClockingTimes();

        // Remove the clocking time.
        for (int i = 0; i < clockingTimes.size(); i++) {
            ClockingTime c = clockingTimes.get(i);

            if (c.equals(clockingTime)) {
                clockingTimes.remove(i);
            }
        }

        selectedEmployee = null;
        String[] messages = {"clocking_times"};
        notifyObservers(messages);
    }

    /**
     * Recover clocking times from a file.
     * @return Whether the operation succeed or failed.
     */
    public int recoverClockingTimesFromFile() {
        ArrayList<ClockingTime> list = new ArrayList<ClockingTime>();
        ClockingTimeDataManager manager = new ClockingTimeDataManager();
        int information = 0;

        try {
            FileDialog fileDialog = new FileDialog(new Frame(),"Chose a file");
            fileDialog.setDirectory("C:\\");
            fileDialog.setFile("*.txt");
            fileDialog.setVisible(true);

            manager.setFilePath(fileDialog.getDirectory() + fileDialog.getFile());
            list = manager.parse();

            getClockingTimes().addAll(list);

            String[] messages = {"clocking_times"};
            notifyObservers(messages);
            information = list.size();
        }
        catch(Exception e) {
            e.printStackTrace();
            information = -1;
        }

        return information;
    }

    /**
     * Update the selected employee.
     * @param newSelectedEmployee The new selected employee.
     */
    public void updateSelectedEmployee(Employee newSelectedEmployee) {
        selectedEmployee = newSelectedEmployee;

        String[] messages = {"selected_employee"};
        notifyObservers(messages);
    }

    /**
     * remove employee selected and clocking times concerned
     */
    public void removeEmployee() {
        ArrayList<Employee> employees = company.getEmployees();

        // Remove the employee.
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);

            if (getSelectedEmployee().getId().equals(employee.getId())) {
                Department department = company.getDepartment(employee);
                department.getEmployees().remove(i);
            }
        }

        // Remove the clocking times of the employee.
        for (int i = getClockingTimes().size() - 1; i >= 0; i--) {
            ClockingTime clockingTime = getClockingTimes().get(i);

            if (getSelectedEmployee().getId().equals(clockingTime.getEmployee().getId())) {
                getClockingTimes().remove(i);
                System.out.println("Test");
            }
        }

        selectedEmployee = null;
        String[] messages = {"employees", "selected_employee", "employee_filter", "clocking_times"};
        notifyObservers(messages);
    }

    /**
     * Get the company.
     * @return The company.
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Set the company.
     * @param company The new company.
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Get the selected employee.
     * @return The selected employee.
     */
    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    /**
     * Get the clocking times.
     * @return The clocking times.
     */
    public ArrayList<ClockingTime> getClockingTimes() {
        return clockingTimes;
    }

    /**
     * Return the current application server.
     * @return the current application server.
     */
    public ServerMultiThread getServerMultiThread() {
        return serverMultiThread;
    }

    /**
     * Set the clocking times.
     * @param clockingTimes The new clocking times.
     */
    public void setClockingTimes(ArrayList<ClockingTime> clockingTimes) {
        if (this.clockingTimes != null) {
            this.clockingTimes.clear();

            if (clockingTimes != null && clockingTimes.size() > 0) {
                this.clockingTimes.addAll(clockingTimes);
            }
        } else {
            this.clockingTimes = clockingTimes;
        }
    }
}
