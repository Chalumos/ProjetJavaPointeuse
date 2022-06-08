package fr.univtours.polytech.projet_tutore.view.timetracker;

import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.view.ViewController;
import fr.univtours.polytech.projet_tutore.view.timetracker.settings.SettingsView;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * Controller which controls the elements of a TimeTrackerView.
 */
public class TimeTrackerViewController extends ViewController {
     /**
     * Label of the current date.
     */
    @FXML
    private Label dateLabel;

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
    private ComboBox<Employee> employeeComboBox;

    /**
     * Label to print information.
     */
    @FXML
    private Label labelCheckInfo;

    /**
     * Try to check an employee.
     */
    @FXML
    public void checkEmployee() {
        Employee employee = employeeComboBox.getValue();

        if (employee == null) {
            getLabelCheckInfo().setStyle("-fx-text-fill: #f54e4e");
            setLabelCheckInfo("Please select an employee.");
        } else {
            if (getView().getController().checkEmployee(employee)) {
                getLabelCheckInfo().setStyle("-fx-text-fill: #11b45a");
                setLabelCheckInfo("The clocking time has been successfully sent.");
            } else {
                getLabelCheckInfo().setStyle("-fx-text-fill: #f54e4e");
                setLabelCheckInfo("An error occurred when sending the clocking time.");
            }
        }
    }

    /**
     * Show the Settings view.
     */
    @FXML
    public void showSettings() {
        SettingsView settingsView = new SettingsView();
        settingsView.show();
    }

    /**
     * Create a view controller without view.
     */
    public TimeTrackerViewController() {}

    @Override
    public TimeTrackerView getView() {
        return (TimeTrackerView) super.getView();
    }

    /**
     * Get the label of the current date.
     * @return The label of the current date.
     */
    public Label getDateLabel() {
        return dateLabel;
    }

    /**
     * Get the label of the current time.
     * @return The label of the current time.
     */
    public Label getTimeLabel() {
        return timeLabel;
    }

    /**
     * Get the label of the rounded current time.
     * @return The label of the rounded current time.
     */
    public Label getRoundedTimeLabel() {
        return roundedTimeLabel;
    }

    /**
     * Get the combo box to select an employee.
     * @return The combo box to select an employee.
     */
    public ComboBox<Employee> getEmployeeComboBox() {
        return employeeComboBox;
    }

    /**
     * Set the date of the current date label.
     * @param newCurrentDate The new current date.
     */
    public void setDateLabel(Date newCurrentDate) {
        dateLabel.setText(newCurrentDate.toString());
    }

    /**
     * Set the time of the current time label.
     * @param newCurrentTime The new current time.
     */
    public void setTimeLabel(Time newCurrentTime) {
        timeLabel.setText(newCurrentTime.toString());
    }

    /**
     * Set the time of the rounded current time label.
     * @param newRoundedCurrentTime The new rounded current time.
     */
    public void setRoundedTimeLabel(Time newRoundedCurrentTime) {
        String time = newRoundedCurrentTime.toString();
        String roundedTime = time.substring(0, time.length() - 3);
        roundedTimeLabel.setText(roundedTime);
    }

    /**
     * Set the employee list of the combo box to select an employee.
     * @param newEmployeeList The new list of employees.
     */
    public void setEmployeeComboBox(ArrayList<Employee> newEmployeeList) {
        employeeComboBox.getItems().clear();
        employeeComboBox.getItems().addAll(newEmployeeList);
    }

    /**
     * Get the label to print information about the check.
     * @return The label to print information about the check.
     */
    public Label getLabelCheckInfo() {
        return labelCheckInfo;
    }

    /**
     * Set the text of the label to print information about the check.
     * @param newText The new text.
     */
    public void setLabelCheckInfo(String newText) {
        this.labelCheckInfo.setText(newText);
    }
}
