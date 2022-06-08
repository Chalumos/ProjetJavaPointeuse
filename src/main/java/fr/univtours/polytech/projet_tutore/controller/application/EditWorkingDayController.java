package fr.univtours.polytech.projet_tutore.controller.application;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.date.Days;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.date.WorkingDay;

/**
 * Controller of the view to edit a working day.
 */
public class EditWorkingDayController extends Controller  {
    /**
     * Controller of the application.
     */
    private ApplicationController applicationController;

    /**
     * The working day in edition.
     */
    private WorkingDay workingDay;

    /**
     * Initialize the working day like this: MONDAY(09:00:00 - 18:00:00).
     */
    public EditWorkingDayController() {
        try {
            applicationController = new ApplicationController();
            workingDay = new WorkingDay(Days.MONDAY, new Time(9, 0, 0), new Time(18, 0, 0));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Initialize the working day.
     * @param applicationController The controller of the application.
     * @param workingDay The working day to edit.
     */
    public EditWorkingDayController(ApplicationController applicationController, WorkingDay workingDay) {
        this.applicationController = applicationController;

        if (workingDay != null) {
            this.workingDay = workingDay;
        }
        else {
            try {
                this.workingDay = new WorkingDay(Days.MONDAY,  new Time(9, 0, 0), new Time(18, 0, 0));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void initialize() {
        String[] messages = {"starting_time", "ending_time"};
        notifyObservers(messages);
    }

    /**
     * Get the working day in edition.
     * @return The working day in edition.
     */
    public WorkingDay getWorkingDay() {
        return workingDay;
    }

    /**
     * Set the working day in edition.
     * @param workingDay The new working day.
     */
    public void setWorkingDay(WorkingDay workingDay) {
        this.workingDay = workingDay;

        String[] messages = {"starting_time", "ending_time"};
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
