package fr.univtours.polytech.projet_tutore.view.application;

import fr.univtours.polytech.projet_tutore.controller.application.ApplicationController;
import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;
import fr.univtours.polytech.projet_tutore.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Controller of the components of the view to add or edit a clocking time.
 */
public class EditClockingTimeViewController extends ViewController {
    /**
     * Title of the view.
     */
    @FXML
    private Label labelTitle;

    /**
     * Date picker of the date of the clocking time.
     */
    @FXML
    private DatePicker datePickerDate;

    /**
     * Error label of the clocking time date.
     */
    @FXML
    private Label labelDateError;

    /**
     * Text field of the time of the clocking time.
     */
    @FXML
    private TextField textFieldTime;

    /**
     * Error label of the time of the clocking time.
     */
    @FXML
    private Label labelTimeError;

    /**
     * Combo box to select the employee of the clocking time.
     */
    @FXML
    private ComboBox<Employee> comboBoxEmployee;

    /**
     * Button to add or edit the clocking time.
     */
    @FXML
    private Button buttonEditClockingTime;

    /**
     * Close the view and cancel the addition or edition of the clocking time.
     */
    @FXML
    public void closeView() {
        getView().close();
    }

    /**
     * Add a new clocking time or edit the clocking time in edition.
     */
    @FXML
    public void editClockingTime() {
        ClockingTime clockingTime = getView().getController().getClockingTime();
        LocalDate localDate = getDatePickerDate().getValue();
        String timeString = getTextFieldTime().getText();
        Employee employee = getComboBoxEmployee().getValue();
        boolean areTextFieldsFilled = true;

        // Check if the text fields are filled.
        if (localDate == null) {
            setLabelDateError("Please select a date like yyyy-mm-dd");
            areTextFieldsFilled = false;
        } else {
            try {
                Date date = new Date(localDate.getDayOfMonth(), localDate.getMonth(), localDate.getYear());

                setLabelDateError("");
                clockingTime.setDate(date);
            } catch (Exception exception) {
                setLabelDateError("Please select a date like yyyy-mm-dd");
                areTextFieldsFilled = false;
            }
        }

        if (timeString.length() != 5
                || !Pattern.matches("^\\d{2}:\\d{2}$", timeString)
                || (timeString.charAt(2) != ':')) {
            setLabelTimeError("Please enter a time like hh:mm");
            areTextFieldsFilled = false;
        } else {
            try {
                int hour = Integer.parseInt(timeString.substring(0, 2));
                int minute = Integer.parseInt(timeString.substring(3, 5));
                Time time = new Time(hour, minute, 0);
                time = time.getTimeRoundedToQuarter();

                setLabelTimeError("");
                clockingTime.setTime(time);
            } catch (Exception exception) {
                setLabelTimeError("Please enter a time like hh:mm");
                areTextFieldsFilled = false;
            }
        }

        if (areTextFieldsFilled) {
            ApplicationController applicationController = getView().getController().getApplicationController();
            ArrayList<ClockingTime> clockingTimes = applicationController.getClockingTimes();

            clockingTime.setEmployee(employee);
            getView().getController().setClockingTime(clockingTime);

            // Addition of the clocking time.
            if (!getView().getController().isEdition())  {
                try {
                    applicationController.getClockingTimes().add(clockingTime);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

            getView().close();

            String[] messages = {"clocking_times"};
            applicationController.notifyObservers(messages);
        }
    }

    @Override
    public EditClockingTimeView getView() {
        return (EditClockingTimeView) super.getView();
    }

    /**
     * Set the text of the tile label.
     * @param newTitle The new title of the view.
     */
    public void setLabelTitle(String newTitle) {
        this.labelTitle.setText(newTitle);
    }

    /**
     * Get the date picker to select the date of the clocking time.
     * @return The date picker to select the date of the clocking time.
     */
    public DatePicker getDatePickerDate() {
        return datePickerDate;
    }

    /**
     * Set the date of the date picker.
     * @param newDate The new date of the date picker.
     */
    public void setDatePickerDate(Date newDate) {
        LocalDate date = LocalDate.of(newDate.getYear(), newDate.getMonth(), newDate.getDay());
        this.datePickerDate.setValue(date);
    }

    /**
     * Get the label to print an error for the date picker.
     * @return The label to print an error for the date picker.
     */
    public Label getLabelDateError() {
        return labelDateError;
    }

    /**
     * Set the content of the error label of the date of the clocking time.
     * @param newDateError The new error.
     */
    public void setLabelDateError(String newDateError) {
        this.labelDateError.setText(newDateError);
    }

    /**
     * Get the text field to enter the time of the clocking time.
     * @return The text field to enter the time of the clocking time.
     */
    public TextField getTextFieldTime() {
        return textFieldTime;
    }

    /**
     * Set the time of the text field of the time of the clocking time.
     * @param newTime The new time of the text field.
     */
    public void setTextFieldTime(Time newTime) {
        String hour = "" + newTime.getHour();
        String minute = "" + newTime.getMinute();

        if (Integer.parseInt(hour) <= 9) hour = "0" + hour;
        if (Integer.parseInt(minute) <= 9) minute = "0" + minute;

        String time = hour + ":" + minute;
        this.textFieldTime.setText(time);
    }

    /**
     * Get the label to print an error for the time text field.
     * @return The label to print an error for the time text field.
     */
    public Label getLabelTimeError() {
        return labelTimeError;
    }

    /**
     * Set the content of the error label of the time of the clocking time.
     * @param newTimeError The new error.
     */
    public void setLabelTimeError(String newTimeError) {
        this.labelTimeError.setText(newTimeError);
    }

    /**
     * Get the combo box to select the employee of the clocking time.
     * @return The combo box to select the employee of the clocking time.
     */
    public ComboBox<Employee> getComboBoxEmployee() {
        return comboBoxEmployee;
    }

    /**
     * Set the text of the addition or edition button.
     * @param newText The new text of the button.
     */
    public void setButtonEditClockingTime(String newText) {
        this.buttonEditClockingTime.setText(newText);
    }
}
