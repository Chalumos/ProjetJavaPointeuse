package fr.univtours.polytech.projet_tutore.controller.application;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.Stub;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.data_manager.ClockingTimeDataManager;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;


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
     * Clocking times of the employees of the company filtered.
     */
    private ArrayList<ClockingTime> filteredClockingTimes;

    /**
     * Create the company.
     */
    public ApplicationController() {
        setCompany(null);
    }

    @Override
    public void initialize() {
        do {
            try {
                setCompany(Stub.generateCompany());
                setClockingTimes(Stub.getClockingTimeList());

                filteredClockingTimes = new ArrayList<>();
                filteredClockingTimes.addAll(clockingTimes);

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
     */
    public void recoverClockingTimesFromFile() {
        ArrayList<ClockingTime> list = new ArrayList<ClockingTime>();
        ClockingTimeDataManager manager = new ClockingTimeDataManager();

        // TODO: Ouvrir une fenêtre pour que l'utilisateur puisse sélectionner le fichier de pointages à ajouter.

        try {
            list = manager.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }

        getClockingTimes().addAll(list);

        String[] messages = {"clocking_times"};
        notifyObservers(messages);
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
     * filters the list of clocking times
     *
     * @param employee   employee selected to filter
     * @param department department selected to filter
     * @param fromDate   fromDate selected to filter
     * @param toDate     toDate selected to filter
     */
    public void filters(Employee employee, Department department, LocalDate fromDate, LocalDate toDate) {
        ArrayList<ClockingTime> employeesFilter = new ArrayList<ClockingTime>();

        // if the last call of filters have retun a empty list reset the list of clocking times
        if (filteredClockingTimes.isEmpty()) {
            filteredClockingTimes.addAll(clockingTimes);
        }

        if (employee != null) {
            // search the clooking time equal to the employee filter
            for (ClockingTime clock : filteredClockingTimes) {
                if (employee.equals(clock.getEmployee())) {
                    employeesFilter.add(clock);
                }
            }
            // apply the filter list to the time clock list
            getFilteredClockingTimes().clear();
            getFilteredClockingTimes().addAll(employeesFilter);
            employeesFilter.clear();
        }

        if (department != null) {
            // search the clooking time equal to the department filter
            for (ClockingTime clock : filteredClockingTimes) {
                if (department.equals(getCompany().getDepartment(clock.getEmployee()))) {
                    employeesFilter.add(clock);
                }
            }
            //apply the filter list to the time clock list
            getFilteredClockingTimes().clear();
            getFilteredClockingTimes().addAll(employeesFilter);
            employeesFilter.clear();
        }
        if (fromDate != null && toDate != null) {
            // search for clocking times that are equal to or after fromDate
            // but before or equal toDate
            for (ClockingTime clock : filteredClockingTimes) {
                LocalDate dateCheck = LocalDate.of(clock.getDate().getYear(), clock.getDate().getMonth(), clock.getDate().getDay());
                if ((dateCheck.isEqual(fromDate) || dateCheck.isAfter(fromDate)) && (dateCheck.isEqual(toDate) || dateCheck.isBefore(toDate))) {
                    employeesFilter.add(clock);
                }
            }
            //apply the filter list to the time clock list
            getFilteredClockingTimes().clear();
            getFilteredClockingTimes().addAll(employeesFilter);
            employeesFilter.clear();
        } else if (fromDate != null) {
            // search for clocking times that are equal to or after fromDate
            for (ClockingTime clock : filteredClockingTimes) {
                LocalDate dateCheck = LocalDate.of(clock.getDate().getYear(), clock.getDate().getMonth(), clock.getDate().getDay());
                if (dateCheck.isEqual(fromDate) || dateCheck.isAfter(fromDate)) {
                    employeesFilter.add(clock);
                }
            }
            //apply the filter list to the time clock list
            getFilteredClockingTimes().clear();
            getFilteredClockingTimes().addAll(employeesFilter);
            employeesFilter.clear();
        } else if (toDate != null) {
            // search for clocking times that before or equal toDate
            for (ClockingTime clock : filteredClockingTimes) {
                LocalDate dateCheck = LocalDate.of(clock.getDate().getYear(), clock.getDate().getMonth(), clock.getDate().getDay());
                if (dateCheck.isEqual(toDate) || dateCheck.isBefore(toDate)) {
                    employeesFilter.add(clock);
                }
            }
            //apply the filter list to the time clock list
            getFilteredClockingTimes().clear();
            getFilteredClockingTimes().addAll(employeesFilter);
            employeesFilter.clear();
        }

        String[] messages = {"clocking_times"};
        notifyObservers(messages);
    }
}
