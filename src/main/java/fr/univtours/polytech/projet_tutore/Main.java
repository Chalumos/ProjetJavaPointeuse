package fr.univtours.polytech.projet_tutore;

import fr.univtours.polytech.projet_tutore.view.application.ApplicationView;
import fr.univtours.polytech.projet_tutore.view.timetracker.TimeTrackerView;

public class Main {
    public static void main(String[] args) {
        TimeTrackerView view = new TimeTrackerView();
        //ApplicationView view = new ApplicationView();
        view.show();
    }
}
