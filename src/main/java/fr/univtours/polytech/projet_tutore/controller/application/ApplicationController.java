package fr.univtours.polytech.projet_tutore.controller.application;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.data_manager.ClockingTimeDataManager;
import fr.univtours.polytech.projet_tutore.model.data_manager.CompanyDataManager;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.socket.MultiThreadedServer;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
    private MultiThreadedServer serverMultiThread;

    /**
     * Clocking times of the employees of the company filtered.
     */
    private ArrayList<ClockingTime> filteredClockingTimes;

    /**
     * Create the company.
     */
    public ApplicationController() {
        setCompany(new Company());
        setClockingTimes(new ArrayList<>());
    }

    @Override
    public void initialize() {
        try {
            // Fetch the employees.
            CompanyDataManager companyDataManager = new CompanyDataManager();
            ArrayList<Company> companyData = companyDataManager.parse();
            if (companyData.size() > 0) {
                setCompany(companyData.get(0));
            } else {
                setCompany(new Company());
            }

            // Fetch the clocking times.
            ClockingTimeDataManager clockingTimeDataManager = new ClockingTimeDataManager();
            setClockingTimes(clockingTimeDataManager.parse());
            setFilteredClockingTimes(new ArrayList<>());
            getFilteredClockingTimes().addAll(getClockingTimes());

            // Initialize and launch the server.
            serverMultiThread = new MultiThreadedServer((clockingTimes) -> {
                getClockingTimes().addAll((ArrayList<ClockingTime>) clockingTimes);
                getFilteredClockingTimes().addAll((ArrayList<ClockingTime>) clockingTimes);

                // TODO Heures supp
                // TODO Get tous les employés présents dans les clicking times
                // TODO Boucler sur ces employés pour recalculer leurs heures supp

                String[] messages = {"clocking_times"};
                notifyObservers(messages);
                return null;
            });
            serverMultiThread.start();

            String[] messages = {"employee_filter", "department_filter", "clocking_times", "employees", "selected_employee"};
            notifyObservers(messages);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

        for (int i = 0; i < filteredClockingTimes.size(); i++) {
            ClockingTime c = filteredClockingTimes.get(i);

            if (c.equals(clockingTime)) {
                filteredClockingTimes.remove(i);
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
        ArrayList<ClockingTime> listClockingTimes = new ArrayList<ClockingTime>();
        ClockingTimeDataManager manager = new ClockingTimeDataManager();
        int information = 0;

        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());

            if (file != null) {
                manager.setFilePath(file.getPath());
                listClockingTimes = manager.parse();

                // If the clocking time is about an employee who isn't in the company,
                // we don't add the clocking time.
                for (int i = 0; i < listClockingTimes.size(); i++) {
                    for (int j = 0; j < getCompany().getEmployees().size(); j++) {
                        ClockingTime clockingTime = listClockingTimes.get(i);
                        Employee employee = getCompany().getEmployees().get(j);

                        if (clockingTime.getEmployee().getId().equals(employee.getId())) {
                            getClockingTimes().add(listClockingTimes.get(i));
                            information++;
                        }
                    }
                }

                String[] messages = {"clocking_times"};
                notifyObservers(messages);
            }
        }
        catch(Exception e) {
            information = -1;
            e.printStackTrace();
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
        Department department = company.getDepartment(getSelectedEmployee());
        boolean isEmployeeRemoved = false;
        int index = 0;

        // Remove the employee from its department.
        do {
            Employee employee = department.getEmployees().get(index);

            if (getSelectedEmployee().getId().equals(employee.getId())) {
                department.getEmployees().remove(index);
                isEmployeeRemoved = true;
            }

            index++;
        } while (!isEmployeeRemoved && index < department.getEmployees().size());

        // Remove the clocking times of the employee.
        for (int i = getClockingTimes().size() - 1; i >= 0; i--) {
            ClockingTime clockingTime = getClockingTimes().get(i);

            if (getSelectedEmployee().getId().equals(clockingTime.getEmployee().getId())) {
                getClockingTimes().remove(i);
            }
        }

        // Remove the filtered clocking times of the employee.
        for (int i = getFilteredClockingTimes().size() - 1; i >= 0; i--) {
            ClockingTime clockingTime = getFilteredClockingTimes().get(i);

            if (getSelectedEmployee().getId().equals(clockingTime.getEmployee().getId())) {
                getFilteredClockingTimes().remove(i);
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
        if (company == null) {
            company = new Company();
        }
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
    public MultiThreadedServer getServerMultiThread() {
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

    /**
     * Get the clocking times filtered.
     *
     * @return The clocking times filtered.
     */
    public ArrayList<ClockingTime> getFilteredClockingTimes() {
        return filteredClockingTimes;
    }

    /**
     * Set the clocking times filtered.
     *
     * @param filteredClockingTimes The new clocking times filtered.
     */
    public void setFilteredClockingTimes(ArrayList<ClockingTime> filteredClockingTimes) {
        if (this.filteredClockingTimes != null) {
            this.filteredClockingTimes.clear();

            if (filteredClockingTimes != null && filteredClockingTimes.size() > 0) {
                this.filteredClockingTimes.addAll(filteredClockingTimes);
            }
        } else {
            this.filteredClockingTimes = filteredClockingTimes;
        }
    }

    /**
     * Filter the list of clocking times.
     *
     * @param employee   Filter on an employee.
     * @param department Filter on a department.
     * @param fromDate   Filter on a starting date.
     * @param toDate     Filter on an ending date.
     */
    public void filters(Employee employee, Department department, LocalDate fromDate, LocalDate toDate) {
        ArrayList<ClockingTime> employeesFilter = new ArrayList<>();

        getFilteredClockingTimes().clear();
        getFilteredClockingTimes().addAll(getClockingTimes());

        // Filter on an employee.
        if (employee != null) {
            // Search the clocking time equal to the employee filter.
            for (ClockingTime clock : getFilteredClockingTimes()) {
                if (employee.getId().equals(clock.getEmployee().getId())) {
                    employeesFilter.add(clock);
                }
            }
            // Apply the filter list to the time clock list.
            getFilteredClockingTimes().clear();
            getFilteredClockingTimes().addAll(employeesFilter);
            employeesFilter.clear();
        }

        // Filter on a department.
        if (department != null) {
            // Search the clocking time equal to the department filter.
            for (ClockingTime clock : getFilteredClockingTimes()) {
                Department employeeDepartment = getCompany().getDepartment(clock.getEmployee());
                if (employeeDepartment != null && department.getName().equals(employeeDepartment.getName())) {
                    employeesFilter.add(clock);
                }
            }
            // Apply the filter list to the time clock list.
            getFilteredClockingTimes().clear();
            getFilteredClockingTimes().addAll(employeesFilter);
            employeesFilter.clear();
        }

        // Filter on the starting date.
        if (fromDate != null) {
            // Search for clocking times that are after the date.
            for (ClockingTime clock : getFilteredClockingTimes()) {
                LocalDate dateCheck = LocalDate.of(clock.getDate().getYear(), clock.getDate().getMonth(), clock.getDate().getDay());
                if (dateCheck.isEqual(fromDate) || dateCheck.isAfter(fromDate)) {
                    employeesFilter.add(clock);
                }
            }
            // Apply the filter list to the time clock list.
            getFilteredClockingTimes().clear();
            getFilteredClockingTimes().addAll(employeesFilter);
            employeesFilter.clear();
        }

        // Filter on the ending date.
        if (toDate != null) {
            // Search for clocking times that are before the date.
            for (ClockingTime clock : filteredClockingTimes) {
                LocalDate dateCheck = LocalDate.of(clock.getDate().getYear(), clock.getDate().getMonth(), clock.getDate().getDay());
                if (dateCheck.isEqual(toDate) || dateCheck.isBefore(toDate)) {
                    employeesFilter.add(clock);
                }
            }
            // Apply the filter list to the time clock list.
            getFilteredClockingTimes().clear();
            getFilteredClockingTimes().addAll(employeesFilter);
            employeesFilter.clear();
        }
        
        String[] messages = {"clocking_times"};
        notifyObservers(messages);
    }
}
