package fr.univtours.polytech.timetracker.view;

import fr.univtours.polytech.timetracker.controller.Observable;

/**
 * The observer (the view) observes an observable (the controller)
 * and updates itself when the observable notifies it.
 */
public interface Observer {
    /**
     * Updates the observer when the observable notifies it.
     * @param observable The observable that the observer observes.
     */
    public void update(Observable observable);
}
