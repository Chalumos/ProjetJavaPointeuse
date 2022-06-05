package fr.univtours.polytech.projet_tutore.view.application;

import fr.univtours.polytech.projet_tutore.controller.Observable;
import fr.univtours.polytech.projet_tutore.controller.application.EditEmployeeController;
import fr.univtours.polytech.projet_tutore.view.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditEmployeeView extends View {
    /**
     * Initialize the controller to null.
     */
    public EditEmployeeView() {
        setController(null);
    }

    @Override
    public void initialize() {
        EditEmployeeController controller = new EditEmployeeController();
        setController(controller);
        getController().initialize();
        getController().setEditionMode(true);

        getViewController().setLabelTitle(getController().isEdition() ? "Employee edition" : "New employee");
        getViewController().setButtonEditEmployee(getController().isEdition() ? "Edit employee" : "Add employee");
    }

    @Override
    public void show() {
        View.launch();
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
