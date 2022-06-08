package fr.univtours.polytech.projet_tutore.view.application.edit;

import fr.univtours.polytech.projet_tutore.controller.Observable;
import fr.univtours.polytech.projet_tutore.controller.application.ApplicationController;
import fr.univtours.polytech.projet_tutore.controller.application.edit.EditClockingTimeController;
import fr.univtours.polytech.projet_tutore.controller.application.edit.EditWorkingDayController;
import fr.univtours.polytech.projet_tutore.model.date.WorkingDay;
import fr.univtours.polytech.projet_tutore.view.View;
import fr.univtours.polytech.projet_tutore.view.application.ApplicationView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * View to edit a working day.
 */
public class EditWorkingDayView extends View {
    /**
     * Stage of the page.
     */
    private Stage stage;

    /**
     * Initialize the controller without reference to the controller of the application.
     */
    public EditWorkingDayView() {
        setController(new EditClockingTimeController(new ApplicationController(), null));
    }

    /**
     * Initialize the controller with a reference to the controller of the application.
     * @param applicationController The controller of the application.
     * @param workingDay The working day to edit.
     */
    public EditWorkingDayView(ApplicationController applicationController, WorkingDay workingDay) {
        setController(new EditWorkingDayController(applicationController, workingDay));
    }

    @Override
    public void initialize() {
        getController().initialize();

        getViewController().setLabelTitle("Edit of " + getController().getWorkingDay().getDay());
    }

    @Override
    public void show() {
        Platform.setImplicitExit(false);
        Platform.runLater(()->{
            try {
                stage = new Stage();
                start(stage);
                Platform.setImplicitExit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        // In the case of the separator for the resources, it's the same for every OS: '/'.
        // So there is no need to use File.separator.
        String fileName = "/fr/univtours/polytech/projet_tutore/view/application/editWorkingDay.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationView.class.getResource(fileName));

        // Creation of the scene.
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setScene(scene);

        // Get the controller which controls the elements of the view.
        setViewController(fxmlLoader.getController());
        getViewController().setView(this);

        // Get the controller of the view.
        initialize();

        // Show the view.
        stage.show();
    }

    @Override
    public void update(Observable observable, String[] messages) {
        WorkingDay workingDay = getController().getWorkingDay();

        for (String message : messages) {
            switch (message) {
                case "starting_time" -> getViewController().setTextFieldStartingTime(workingDay.getStartTime());
                case "ending_time" -> getViewController().setTextFieldEndingTime(workingDay.getEndTime());
            }
        }
    }

    /**
     * Close the view.
     */
    public void close () {
        stage.close();
    }

    @Override
    public EditWorkingDayController getController() {
        return (EditWorkingDayController) super.getController();
    }

    @Override
    public EditWorkingDayViewController getViewController() {
        return (EditWorkingDayViewController) super.getViewController();
    }
}
