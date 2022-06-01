package fr.univtours.polytech.projet_tutore.controller.application;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.Stub;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.data.ClockingTimeDataManager;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;

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
     * remove employee selected and clocking times concerned
     */
    public void removeEmployee() {
        for (Department department : company.getDepartments()) {
            for (int i = 0; i < department.getEmployees().size(); i++) {
                if (getSelectedEmployee().getId().equals(department.getEmployees().get(i).getId())) {
                    department.getEmployees().remove(i);
                }
            }
            for (int i=0;i<Stub.getClockingTimeList().size();i++){
                if(Stub.getClockingTimeList().get(i).getEmployee().equals(selectedEmployee)){
                    Stub.getClockingTimeList().remove(i);
                }
            }
        }
        selectedEmployee = null;
        String[] messages = {"employees", "selected_employee", "employee_filter","clocking_times"};
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
}
