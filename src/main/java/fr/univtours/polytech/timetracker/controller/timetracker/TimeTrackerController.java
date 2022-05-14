package fr.univtours.polytech.timetracker.controller.timetracker;

import fr.univtours.polytech.timetracker.controller.Observable;
import fr.univtours.polytech.timetracker.model.date.Date;
import fr.univtours.polytech.timetracker.model.date.Time;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


/**
 * Controller to manage the time-tracker view.
 */
public class TimeTrackerController extends Observable {
    /**
     * Label of the current date.
     */
    @FXML
    private Label dateLabel;

    /**
     * Button to access settings.
     */
    @FXML
    private Button settingsButton;

    /**
     * Label of the current time.
     */
    @FXML
    private Label timeLabel;

    /**
     * Label of the rounded current time.
     */
    @FXML
    private Label roundedTimeLabel;

    /**
     * Combo box to select an employee.
     */
    @FXML
    private ComboBox<String> employeeComboBox;

    /**
     * Button to check an employee.
     */
    @FXML
    private Button checkEmployeeButton;

    /**
     * Label to print the errors.
     */
    @FXML
    private Label errorLabel;

    /**
     * Try to check an employee.
     */
    @FXML
    void checkEmployee() {
        System.out.println("Check employee");
    }

    /**
     * Show the Settings view.
     */
    @FXML
    void showSettings() {
        System.out.println("Show Settings view");
    }

    /**
     * Create the controller.
     */
    public TimeTrackerController() {

    }

    /**
     * Get the date printed on the time-tracker.
     * @return The date printed on the time-tracker.
     */
    public Label getDateLabel() {
        return dateLabel;
    }

    /**
     * Get the button to access settings.
     * @return The button to access settings.
     */
    public Button getSettingsButton() {
        return settingsButton;
    }

    /**
     * Get the time printed on the time-tracker.
     * @return The time printed on the time-tracker.
     */
    public Label getTimeLabel() {
        return timeLabel;
    }

    /**
     * Get the time printed on the time-tracker.
     * @return The time printed on the time-tracker.
     */
    public Label getRoundedTimeLabel() {
        return roundedTimeLabel;
    }

    /**
     * Get the combo box to select an employee.
     * @return The combo box to select an employee.
     */
    public ComboBox<String> getEmployeeComboBox() {
        return employeeComboBox;
    }

    /**
     * Get the button to check an employee.
     * @return The button to check an employee.
     */
    public Button getCheckEmployeeButton() {
        return checkEmployeeButton;
    }

    /**
     * Get the label to print errors.
     * @return The label to print errors.
     */
    public Label getErrorLabel() {
        return errorLabel;
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

        getDateLabel().setText(newDate.toString());
    }

    public void setSettingsButton(Button settingsButton) {
        this.settingsButton = settingsButton;
    }

    /**
     * Set the time printed on the time-tracker.
     * @param newTime The new time.
     * @throws Exception If the new time is null.
     */
    public void setTimeLabel(Time newTime) throws Exception {
        if (newTime == null) {
            throw new Exception("The time can't be null.");
        }

        getTimeLabel().setText(newTime.toString());
    }

    /**
     * Set the rounded time printed on the time-tracker.
     * @param newRoundedTime The new rounded time.
     * @throws Exception If the new rounded time is null.
     */
    public void setRoundedTimeLabel(Time newRoundedTime) throws Exception {
        if (newRoundedTime == null) {
            throw new Exception("The rounded time can't be null.");
        }

        getRoundedTimeLabel().setText(newRoundedTime.toString());
    }

    /**
     * Set the combo box to select an employee.
     * @param newComboBox The new combo box.
     */
    public void setEmployeeComboBox(ComboBox<String> newComboBox) {
        this.employeeComboBox = newComboBox;
    }

    public void setCheckEmployeeButton(Button checkEmployeeButton) {
        this.checkEmployeeButton = checkEmployeeButton;
    }

    /**
     * Set the error of the label.
     * @param newError The new error.
     */
    public void setErrorLabel(String newError) {
        getErrorLabel().setText(newError);
    }
}
