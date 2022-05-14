package fr.univtours.polytech.timetracker.controller;

import fr.univtours.polytech.timetracker.model.date.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class MainController extends Observable {
    // TODO: Déclarer ici tous les attributs représentants des données (model)

    /**
     * Label of the current date.
     */
    @FXML
    private Label dateLabel;

    // TODO: Déclarer ici les éléments de l'IHM
    @FXML
    void checkEmployee() {
        System.out.println("Check employee");
    }

    // TODO: Déclarer ici les méthodes des events de l'IHM

    public MainController() {
    }


    /**
     * Get the date printed on the time-tracker.
     * @return The date printed on the time-tracker.
     */
    public Label getDateLabel() {
        return dateLabel;
    }


    /**
     * Set the date printed on the time-tracker.
     * @param newDate The new date.
     * @throws Exception If the new date is null.
     */
    public void setDateLabel(Date newDate) throws Exception {
        if (newDate == null) {
            throw new Exception("The date can't be null.");
        }

        dateLabel.setText(newDate.toString());
    }
}
