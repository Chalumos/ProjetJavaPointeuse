package fr.univtours.polytech.projet_tutore.controller.application;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.Stub;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.data.ClockingTimeDataManager;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;
import javafx.scene.control.TableView;

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
     * Update the selected employee.
     * @param newSelectedEmployee The new selected employee.
     */
    public void updateSelectedEmployee(Employee newSelectedEmployee) {
        selectedEmployee = newSelectedEmployee;

        String[] messages = {"selected_employee"};
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
            list = manager.parseClockingTime();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        getClockingTimes().addAll(list);

        String[] messages = {"clocking_times"};
        notifyObservers(messages);
    }

    /**
     * remove employee selected
     */
    public void removeEmployee() {
        for (Department department : company.getDepartments()) {
            for (int i = 0; i < department.getEmployees().size(); i++) {
                if (getSelectedEmployee().getId().equals(department.getEmployees().get(i).getId())) {
                    department.getEmployees().remove(i);
                }
            }
        }
        selectedEmployee = null;
        String[] messages = {"employees", "selected_employee"};
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
     * @return The clocking times filtered.
     */
    public ArrayList<ClockingTime> getFilteredClockingTimes() {
        return filteredClockingTimes;
    }

    /**
     * Set the clocking times filtered.
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
    public void filterEmployee(Employee employee, Department department){
        filteredClockingTimes.addAll(clockingTimes);
        List<ClockingTime> employeesFilter = new ArrayList<ClockingTime>();

        if (employee != null) {
            System.out.println("employe");
            employeesFilter = filteredClockingTimes.stream().filter(clockingTime -> clockingTime.getEmployee() == employee).toList();
        }

            if (department != null) {
            System.out.println("department");
            employeesFilter = filteredClockingTimes.stream().filter(clockingTime -> getCompany().getDepartment(clockingTime.getEmployee()).equals(department)).toList();

        }

        if (!employeesFilter.isEmpty()) {
            getFilteredClockingTimes().clear();
            getFilteredClockingTimes().addAll(employeesFilter);
        }

        String[] messages = {"clocking_times"};
        notifyObservers(messages);
    }
}
