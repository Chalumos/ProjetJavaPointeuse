package fr.univtours.polytech.projet_tutore.controller.application.edit;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.controller.application.ApplicationController;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.date.Schedule;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;

/**
 * Controller of the view to add or edit an employee.
 */
public class EditEmployeeController extends Controller {
    /**
     * Controller of the application.
     */
    private final ApplicationController applicationController;

    /**
     * Whether the employee is in edition or not (in addition).
     */
    private final boolean editionMode;

    /**
     * The employee in addition or in edition
     */
    private Employee employee;

    /**
     * Department of the employee.
     */
    private Department department;

    /**
     * Initialize the mode to addition.
     */
    public EditEmployeeController() {
        applicationController = new ApplicationController();
        editionMode = false;
        employee = new Employee();
    }

    /**
     * Initialize the mode to addition.
     * @param applicationController The controller of the application.
     * @param employee The employee to edit.
     */
    public EditEmployeeController(ApplicationController applicationController, Employee employee) {
        this.applicationController = applicationController;
        Company company = getApplicationController().getCompany();

        // If the employee is not null, edition mode.
        if (employee != null) {
            this.employee = employee;
            department = company.getDepartment(employee);
            editionMode = true;
        }
        // Else, addition mode.
        else {
            this.employee = new Employee("", "", new Schedule());

            // If there is no department in the company, we added a default one.
            if (company.getDepartments().size() == 0) {
                try {
                    company.addDepartment(new Department("Default department"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            department = company.getDepartments().get(0);
            editionMode = false;
        }
    }

    @Override
    public void initialize() {
        String[] messages = {"firstname", "lastname", "department"};
        notifyObservers(messages);
    }

    /**
     * Whether the employee is in edition or not.
     * @return Whether the employee is in edition or not.
     */
    public boolean isEdition() {
        return editionMode;
    }

    /**
     * Get the employee in addition or in edition.
     * @return The employee in addition or in edition.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set the employee in addition or in edition.
     * @param employee The new employee in addition or in edition.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;

        String[] messages = {"firstname", "lastname", "department"};
        notifyObservers(messages);
    }

    /**
     * Get the controller of the application.
     * @return The controller of the application.
     */
    public ApplicationController getApplicationController() {
        return applicationController;
    }

    /**
     * Get the department of the employee.
     * @return The department of the employee.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Set the department of the employee.
     * @param department The new department of the employee.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }
}
