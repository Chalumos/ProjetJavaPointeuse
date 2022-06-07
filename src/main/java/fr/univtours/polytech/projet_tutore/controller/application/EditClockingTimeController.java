package fr.univtours.polytech.projet_tutore.controller.application;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;

/**
 * Controller of the view to add or edit a clocking time.
 */
public class EditClockingTimeController extends Controller {
    /**
     * Controller of the application.
     */
    private ApplicationController applicationController;

    /**
     * Whether the employee is in edition or not (in addition).
     */
    private boolean editionMode;

    /**
     * The employee in addition or in edition
     */
    private ClockingTime clockingTime;

    /**
     * Initialize the mode to addition.
     */
    public EditClockingTimeController() {
        applicationController = new ApplicationController();
        editionMode = false;
        clockingTime = new ClockingTime(new Employee(), new Date(), new Time());
    }

    /**
     * Initialize the mode to addition.
     * @param applicationController The controller of the application.
     * @param clockingTime The clocking time to edit.
     */
    public EditClockingTimeController(ApplicationController applicationController, ClockingTime clockingTime) {
        this.applicationController = applicationController;
        Company company = getApplicationController().getCompany();

        // If the clocking time is not null, edition mode.
        if (clockingTime != null) {
            this.clockingTime = clockingTime;
            editionMode = true;
        }
        // Else, addition mode.
        else {
            this.clockingTime = new ClockingTime(company.getEmployees().get(0), new Date(), new Time());
            editionMode = false;
        }
    }

    @Override
    public void initialize() {
        String[] messages = {"date", "time", "employee"};
        notifyObservers(messages);
    }

    /**
     * Whether the clocking time is in edition or not.
     * @return Whether the clocking time is in edition or not.
     */
    public boolean isEdition() {
        return editionMode;
    }

    /**
     * Get the clocking time in addition or in edition.
     * @return The clocking time in addition or in edition.
     */
    public ClockingTime getClockingTime() {
        return clockingTime;
    }

    /**
     * Set the clocking time in addition or in edition.
     * @param clockingTime The new clocking time in addition or in edition.
     */
    public void setClockingTime(ClockingTime clockingTime) {
        this.clockingTime = clockingTime;

        String[] messages = {"date", "time", "employee"};
        notifyObservers(messages);
    }

    /**
     * Get the controller of the application.
     * @return The controller of the application.
     */
    public ApplicationController getApplicationController() {
        return applicationController;
    }
}
