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

        if (company != null) {
            String[] messages = {"employee_filter", "department_filter", "clocking_times", "employees"};
            notifyObservers(messages);
        }
    }

    /**
     * Get the selected employee.
     * @return The selected employee.
     */
    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    /**
     * to recover clocking times which are not in the application
     * @return a list of clocking times
     */
    public void recoverClockingTime(){
        ArrayList<ClockingTime> list=new ArrayList<ClockingTime>();
        ClockingTimeDataManager manager = new ClockingTimeDataManager();
        try{
            list = manager.parseClockingTime();
        }
        catch(Exception e){
            e.printStackTrace();
        }

       // clockingTimes.addAll(list);

        String[] messages = {"clocking_times"};
        notifyObservers(messages);
    }

    /**
     * remove employee selected
     */
    public void removeEmployee(){
        for (Department department:company.getDepartments()) {
            for(int i=0;i<department.getEmployees().size();i++){
                if(selectedEmployee.getId().equals(department.getEmployees().get(i).getId())){
                    department.getEmployees().remove(i);
                }
            }
        }
        selectedEmployee=null;
        String[] messages = {"employees", "selected_employee"};
        notifyObservers(messages);
    }
}
