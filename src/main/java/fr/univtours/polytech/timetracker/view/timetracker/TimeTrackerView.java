package fr.univtours.polytech.timetracker.view.timetracker;

import fr.univtours.polytech.timetracker.controller.timetracker.TimeTrackerController;
import fr.univtours.polytech.timetracker.controller.Observable;
import fr.univtours.polytech.timetracker.model.date.Date;
import fr.univtours.polytech.timetracker.model.date.Time;
import fr.univtours.polytech.timetracker.view.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * View of the time-tracker.
 */
public class TimeTrackerView extends View {
     /**
     * Initialize the controller to null.
     */
    public TimeTrackerView() {
        setController(null);
    }

    @Override
    public void show() {
        View.launch();
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

        // Get the controller which controls the elements of the view.
        setViewController(fxmlLoader.getController());
        getViewController().setView(this);

        // Get the controller of the view.
        TimeTrackerController controller = new TimeTrackerController();
        setController(controller);
        controller.initialize();

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
    public void update(Observable observable, String[] messages) {
        try {
            for (String message : messages) {
                switch (message) {
                    case "date" -> {
                        // Update of the current date.
                        Date date = getController().getCurrentDate();
                        getViewController().setDateLabel(date);
                    }
                    case "time" -> {
                        // Update of the current and rounded time.
                        Time time = getController().getCurrentTime();
                        getViewController().setTimeLabel(time);
                        getViewController().setRoundedTimeLabel(time.getTimeRoundedToQuarter());
                    }
                    case "employees" -> {
                        // Update of the employee list.
                        getViewController().setEmployeeComboBox(getController().getEmployees());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TimeTrackerController getController() {
        return (TimeTrackerController) super.getController();
    }

    @Override
    public TimeTrackerViewController getViewController() {
        return (TimeTrackerViewController) super.getViewController();
    }
}
