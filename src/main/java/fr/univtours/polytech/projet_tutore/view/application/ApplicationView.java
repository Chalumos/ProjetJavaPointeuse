package fr.univtours.polytech.projet_tutore.view.application;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.controller.Observable;
import fr.univtours.polytech.projet_tutore.controller.application.ApplicationController;
import fr.univtours.polytech.projet_tutore.controller.timetracker.TimeTrackerController;
import fr.univtours.polytech.projet_tutore.view.View;
import fr.univtours.polytech.projet_tutore.view.ViewController;
import fr.univtours.polytech.projet_tutore.view.timetracker.TimeTrackerView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ApplicationView extends View {
    /**
     * Initialize the controller to null.
     */
    public ApplicationView() {
        setController(null);
    }

    @Override
    public void update(Observable observable, String[] messages) {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void show() {
        View.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // In the case of the separator for the resources, it's the same for every OS: '/'.
        // So there is no need to use File.separator.
        String fileName = "/fr/univtours/polytech/projet_tutore/view/application/applicationView.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationView.class.getResource(fileName));

        // Creation of the scene.
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Time tracker monitoring");
        stage.setResizable(false);
        stage.setScene(scene);

        // Get the controller which controls the elements of the view.
        setViewController(fxmlLoader.getController());
        getViewController().setView(this);

        // Get the controller of the view.
        ApplicationController controller = new ApplicationController();
        setController(controller);
        controller.initialize();

        // Show the view.
        stage.show();
    }

    @Override
    public ApplicationController getController() {
        return (ApplicationController) super.getController();
    }

    @Override
    public ApplicationViewController getViewController() {
        return (ApplicationViewController) super.getViewController();
    }
}
