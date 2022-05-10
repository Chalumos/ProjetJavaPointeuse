package fr.univtours.polytech.timetracker.view;

import fr.univtours.polytech.timetracker.controller.Observable;

public interface Observer {
    public void update(Observable observable);
}
