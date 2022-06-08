package fr.univtours.polytech.projet_tutore.view.application.edit;

import fr.univtours.polytech.projet_tutore.controller.Observable;
import fr.univtours.polytech.projet_tutore.controller.application.ApplicationController;
import fr.univtours.polytech.projet_tutore.controller.application.edit.EditClockingTimeController;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;
import fr.univtours.polytech.projet_tutore.view.View;
import fr.univtours.polytech.projet_tutore.view.application.ApplicationView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * View to add or edit a clocking time.
 */
public class EditClockingTimeView extends View {
    /**
     * Stage of the page.
     */
    private Stage stage;

    /**
     * Initialize the controller in addition mode without reference to the controller of the application.
     */
    public EditClockingTimeView() {
        setController(new EditClockingTimeController(new ApplicationController(), null));
    }

    /**
     * Initialize the controller in addition mode with a reference to the controller of the application.
     * @param applicationController The controller of the application.
     * @param clockingTime The clocking time to edit.
     */
    public EditClockingTimeView(ApplicationController applicationController, ClockingTime clockingTime) {
        setController(new EditClockingTimeController(applicationController, clockingTime));
    }

    @Override
    public void initialize() {
        getController().initialize();

        getViewController().setLabelTitle(getController().isEdition() ? "Clocking time edition" : "New clocking time");
        getViewController().setButtonEditClockingTime(getController().isEdition() ? "Edit clocking time" : "Add clocking time");

        Company company = getController().getApplicationController().getCompany();
        getViewController().getComboBoxEmployee().getItems().setAll(company.getEmployees());
        getViewController().getComboBoxEmployee().setValue(getController().getClockingTime().getEmployee());
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
        String fileName = "/fr/univtours/polytech/projet_tutore/view/application/editClockingTime.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationView.class.getResource(fileName));

        // Creation of the scene.
        Scene scene = new Scene(fxmlLoader.load());
        setIcon(stage);
        stage.setResizable(false);
        stage.setScene(scene);

        // Get the controller which controls the elements of the view.
        setViewController(fxmlLoader.getController());
        getViewController().setView(this);

        // Get the controller of the view.
        initialize();

        // Show the view.
        stage.setTitle(getController().isEdition() ? "Edit clocking time" : "New clocking time");
        stage.show();
    }

    @Override
    public void update(Observable observable, String[] messages) {
        ClockingTime clockingTime = getController().getClockingTime();

        for (String message : messages) {
            switch (message) {
                case "date" -> getViewController().setDatePickerDate(clockingTime.getDate());
                case "time" -> getViewController().setTextFieldTime(clockingTime.getTime());
                case "employee" -> {
                    Employee employee = clockingTime.getEmployee();
                    getViewController().getComboBoxEmployee().setValue(employee);
                }
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
    public EditClockingTimeController getController() {
        return (EditClockingTimeController) super.getController();
    }

    @Override
    public EditClockingTimeViewController getViewController() {
        return (EditClockingTimeViewController) super.getViewController();
    }
}
