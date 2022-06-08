package fr.univtours.polytech.projet_tutore.view.application.edit;

import fr.univtours.polytech.projet_tutore.controller.application.ApplicationController;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.date.WorkingDay;
import fr.univtours.polytech.projet_tutore.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

/**
 * Controller of the components of the view to edit a working day.
 */
public class EditWorkingDayViewController extends ViewController {
    /**
     * Title of the view.
     */
    @FXML
    private Label labelTitle;

    /**
     * Text field of the starting time of the working day.
     */
    @FXML
    private TextField textFieldStartingTime;

    /**
     * Error label of the starting time.
     */
    @FXML
    private Label labelStartingTimeError;

    /**
     * Text field of the ending time of the working day.
     */
    @FXML
    private TextField textFieldEndingTime;

    /**
     * Error label of the ending time.
     */
    @FXML
    private Label labelEndingTimeError;

    /**
     * Button to edit the working day.
     */
    @FXML
    private Button buttonEditWorkingDay;

    /**
     * Close the view and cancel edition of the working day.
     */
    @FXML
    public void closeView() {
        getView().close();
    }

    /**
     * Edit the working day in edition.
     */
    @FXML
    public void editWorkingDay() {
        WorkingDay workingDay = getView().getController().getWorkingDay();
        String startingTimeString = getTextFieldStartingTime().getText();
        String endingTimeString = getTextFieldEndingTime().getText();
        boolean areTextFieldsFilled = true;

        // Set the times of the working day to null.
        if (startingTimeString.length() == 0 && endingTimeString.length() == 0) {
            try {
                setLabelStartingTimeError("");
                workingDay.setStartTime(null);
                setLabelEndingTimeError("");
                workingDay.setEndTime(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            if (startingTimeString.length() != 5
                    || !Pattern.matches("^\\d{2}:\\d{2}$", startingTimeString)
                    || (startingTimeString.charAt(2) != ':')) {
                setLabelStartingTimeError("Please enter a time like hh:mm");
                areTextFieldsFilled = false;
            } else {
                try {
                    int hour = Integer.parseInt(startingTimeString.substring(0, 2));
                    int minute = Integer.parseInt(startingTimeString.substring(3, 5));
                    Time time = new Time(hour, minute, 0);
                    time = time.getTimeRoundedToQuarter();

                    setLabelStartingTimeError("");
                    workingDay.setStartTime(time);
                } catch (Exception exception) {
                    setLabelStartingTimeError("Please enter a valid time");
                    areTextFieldsFilled = false;
                }
            }

            if (endingTimeString.length() != 5
                    || !Pattern.matches("^\\d{2}:\\d{2}$", endingTimeString)
                    || (endingTimeString.charAt(2) != ':')) {
                setLabelEndingTimeError("Please enter a time like hh:mm");
                areTextFieldsFilled = false;
            } else {
                try {
                    int hour = Integer.parseInt(endingTimeString.substring(0, 2));
                    int minute = Integer.parseInt(endingTimeString.substring(3, 5));
                    Time time = new Time(hour, minute, 0);
                    time = time.getTimeRoundedToQuarter();

                    setLabelEndingTimeError("");
                    workingDay.setEndTime(time);
                } catch (Exception exception) {
                    setLabelEndingTimeError("Please enter a valid time");
                    areTextFieldsFilled = false;
                }
            }
        }

        if (areTextFieldsFilled) {
            ApplicationController applicationController = getView().getController().getApplicationController();

            getView().getController().setWorkingDay(workingDay);

            getView().close();

            String[] messages = {"selected_employee"};
            applicationController.notifyObservers(messages);
        }
    }

    @Override
    public EditWorkingDayView getView() {
        return (EditWorkingDayView) super.getView();
    }

    /**
     * Set the text of the tile label.
     * @param newTitle The new title of the view.
     */
    public void setLabelTitle(String newTitle) {
        this.labelTitle.setText(newTitle);
    }

    /**
     * Get the text field to enter the starting time of the working day.
     * @return The text field to enter the starting time of the working day.
     */
    public TextField getTextFieldStartingTime() {
        return textFieldStartingTime;
    }

    /**
     * Set the time of the text field of the starting time of the working day.
     * @param newTime The new starting time of the text field.
     */
    public void setTextFieldStartingTime(Time newTime) {
        String hour = "";
        String minute = "";
        String time = "";

        if (newTime != null) {
            hour += newTime.getHour();
            minute += newTime.getMinute();

            if (Integer.parseInt(hour) <= 9) hour = "0" + hour;
            if (Integer.parseInt(minute) <= 9) minute = "0" + minute;

            time = hour + ":" + minute;
        }

        this.textFieldStartingTime.setText(time);
    }

    /**
     * Get the label to print an error for the starting time text field.
     * @return The label to print an error for the starting time text field.
     */
    public Label getLabelStartingTimeError() {
        return labelStartingTimeError;
    }

    /**
     * Set the content of the error label of the starting time of the working day.
     * @param newTimeError The new error.
     */
    public void setLabelStartingTimeError(String newTimeError) {
        this.labelStartingTimeError.setText(newTimeError);
    }

    /**
     * Get the text field to enter the ending time of the working day.
     * @return The text field to enter the ending time of the working day.
     */
    public TextField getTextFieldEndingTime() {
        return textFieldEndingTime;
    }

    /**
     * Set the time of the text field of the ending time of the working day.
     * @param newTime The new ending time of the text field.
     */
    public void setTextFieldEndingTime(Time newTime) {
        String hour = "";
        String minute = "";
        String time = "";

        if (newTime != null) {
            hour += newTime.getHour();
            minute += newTime.getMinute();

            if (Integer.parseInt(hour) <= 9) hour = "0" + hour;
            if (Integer.parseInt(minute) <= 9) minute = "0" + minute;

            time = hour + ":" + minute;
        }

        this.textFieldEndingTime.setText(time);
    }

    /**
     * Get the label to print an error for the ending time text field.
     * @return The label to print an error for the ending time text field.
     */
    public Label getLabelEndingTimeError() {
        return labelEndingTimeError;
    }

    /**
     * Set the content of the error label of the ending time of the working day.
     * @param newTimeError The new error.
     */
    public void setLabelEndingTimeError(String newTimeError) {
        this.labelEndingTimeError.setText(newTimeError);
    }
}
