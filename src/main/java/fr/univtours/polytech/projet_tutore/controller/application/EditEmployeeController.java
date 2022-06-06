package fr.univtours.polytech.projet_tutore.controller.application;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;

public class EditEmployeeController extends Controller {
    /**
     * Whether the employee is in edition or not (in addition).
     */
    private boolean editionMode;

    /**
     * The employee in addition or in edition
     */
    private Employee employee;

    /**
     * Set the mode to addition.
     */
    public EditEmployeeController() {
        setEditionMode(false);
    }

    @Override
    public void initialize() {
        if (!isEdition()) {
            setEmployee(new Employee());
        }
    }

    /**
     * Whether the employee is in edition or not.
     * @return Whether the employee is in edition or not.
     */
    public boolean isEdition() {
        return editionMode;
    }

    /**
     * Set the mode to addition or edition.
     * @param isEdition Whether the new mode is edition or not.
     */
    public void setEditionMode(boolean isEdition) {
        editionMode = isEdition;
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
    }
}
