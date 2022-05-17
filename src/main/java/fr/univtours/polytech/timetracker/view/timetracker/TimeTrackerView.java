package fr.univtours.polytech.timetracker.view.timetracker;

import fr.univtours.polytech.timetracker.controller.timetracker.TimeTrackerController;
import fr.univtours.polytech.timetracker.controller.Observable;
import fr.univtours.polytech.timetracker.model.date.Date;
import fr.univtours.polytech.timetracker.model.date.Time;
import fr.univtours.polytech.timetracker.view.Observer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * View of the time-tracker.
 */
public class TimeTrackerView extends Application implements Observer {
    /**
     * Controller of the view.
     */
    private TimeTrackerController controller;

    /**
     * Initialize the controller to null.
     */
    public TimeTrackerView() {
        controller = null;
    }

    /**
     * Show the view.
     */
    public void show() {
        TimeTrackerView.launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // In the case of the separator for the resources, it's the same for every OS: '/'.
        // So there is no need to use File.separator.
        String fileName = "/fr/univtours/polytech/timetracker/mainView.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(TimeTrackerView.class.getResource(fileName));

        // Creation of the scene.
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Time tracker emulator");
        stage.setResizable(false);
        stage.setScene(scene);

        // Get the controller of the view.
        TimeTrackerController controller = fxmlLoader.getController();
        controller.getEmployeeComboBox().getItems().add("> Select an employee");
        controller.getEmployeeComboBox().getItems().add("John Doe");
        controller.getEmployeeComboBox().getItems().add("Jane Doe");
        controller.getEmployeeComboBox().getItems().add("Jim Doe");
        controller.getEmployeeComboBox().getItems().add("Jack Doe");
        controller.getEmployeeComboBox().getItems().add("Jojo Doe");
        controller.getEmployeeComboBox().getItems().add("Jean Doe");
        controller.getEmployeeComboBox().getItems().add("John Doe");
        controller.getEmployeeComboBox().getItems().add("Jane Doe");
        controller.getEmployeeComboBox().getItems().add("Jim Doe");
        controller.getEmployeeComboBox().getItems().add("Jack Doe");
        controller.getEmployeeComboBox().getItems().add("Jojo Doe");
        controller.getEmployeeComboBox().getItems().add("Jean Doe");
        setController(controller);

        // Initialization of the time-tracker.
        getController().updateTime();
        update(getController());

        // Update of the time-tracker every second.
        KeyFrame frame = new KeyFrame(Duration.seconds(1), event -> {
            getController().updateTime();
        });
        Timeline timeline = new Timeline(frame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // Show the view.
        stage.show();
    }

    @Override
    public void update(Observable observable) {
        try {
            Date date = getController().getCurrentDate();
            Time time = getController().getCurrentTime();

            getController().setDateLabel(date);
            getController().setTimeLabel(time);
            getController().setRoundedTimeLabel(time.getTimeRoundedToQuarter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the controller of the view.
     * @return The controller of the view.
     */
    public TimeTrackerController getController() {
        return controller;
    }

    /**
     * Set the controller of the view.
     * @param controller The new controller of the view.
     */
    public void setController(TimeTrackerController controller) {
        this.controller = controller;
        this.controller.register(this);
        // update(this.controller);
    }
}
