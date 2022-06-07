package fr.univtours.polytech.projet_tutore.view.application;

import fr.univtours.polytech.projet_tutore.controller.Observable;
import fr.univtours.polytech.projet_tutore.controller.application.ApplicationController;
import fr.univtours.polytech.projet_tutore.controller.application.EditEmployeeController;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.view.View;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditEmployeeView extends View {
    /**
     * Settings page.
     */
    private Stage stage;

    /**
     * Initialize the controller in addition mode without reference to the controller of the application.
     */
    public EditEmployeeView() {
        setController(new EditEmployeeController(new ApplicationController(), null));
    }

    /**
     * Initialize the controller in addition mode with a reference to the controller of the application.
     * @param applicationController The controller of the application.
     * @param employee The employee to edit.
     */
    public EditEmployeeView(ApplicationController applicationController, Employee employee) {
        setController(new EditEmployeeController(applicationController, employee));
    }

    @Override
    public void initialize() {
        getController().initialize();

        getViewController().setLabelTitle(getController().isEdition() ? "Employee edition" : "New employee");
        getViewController().setButtonEditEmployee(getController().isEdition() ? "Edit employee" : "Add employee");

        Company company = getController().getApplicationController().getCompany();
        getViewController().getComboBoxDepartment().getItems().setAll(company.getDepartments());
        getViewController().getComboBoxDepartment().setValue(getController().getDepartment());
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
        String fileName = "/fr/univtours/polytech/projet_tutore/view/application/editEmployeeView.fxml";
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
        stage.setTitle(getController().isEdition() ? "Edit employee" : "New employee");
        stage.show();
    }

    @Override
    public void update(Observable observable, String[] messages) {
        Company company = getController().getApplicationController().getCompany();
        Employee employee = getController().getEmployee();

        for (String message : messages) {
            switch (message) {
                case "firstname" -> getViewController().setTextFieldFirstName(employee.getFirstName());
                case "lastname" -> getViewController().setTextFieldLastName(employee.getLastName());
                case "department" -> {
                    Department department = company.getDepartment(employee);
                    getViewController().getComboBoxDepartment().setValue(department);
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
    public EditEmployeeController getController() {
        return (EditEmployeeController) super.getController();
    }

    @Override
    public EditEmployeeViewController getViewController() {
        return (EditEmployeeViewController) super.getViewController();
    }
}
