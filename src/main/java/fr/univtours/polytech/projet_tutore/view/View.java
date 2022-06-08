package fr.univtours.polytech.projet_tutore.view;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

/**
 * View of a HIM.
 */
public abstract class View extends Application implements Observer {
    /**
     * Controller which controls the elements of the view.
     */
    private ViewController viewController;

    /**
     * Controller of the view.
     */
    private Controller controller;

    /**
     * Initialize the view.
     */
    public abstract void initialize();

    /**
     * Set the icon of the view.
     */
    public void setIcon(Stage stage) {
        // Get the project root path.
        File root = new File("");

        // Create the path for the packages.
        String packages = File.separator + "src" +
                File.separator + "main" +
                File.separator + "resources" +
                File.separator + "fr" +
                File.separator + "univtours" +
                File.separator + "polytech" +
                File.separator + "projet_tutore" +
                File.separator + "img" +
                File.separator;

        String path = root.getAbsolutePath() + packages + "icon.png";

        stage.getIcons().add(new Image(path));
    }

    /**
     * Show the view.
     */
    public abstract void show();

    /**
     * Get the controller of the view.
     * @return The controller of the view.
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Get the controller which controls the elements of the view.
     * @return The controller which controls the elements of the view.
     */
    public ViewController getViewController() {
        return viewController;
    }

    /**
     * Set the controller of the view.
     * @param controller The new controller of the view.
     */
    public void setController(Controller controller) {
        this.controller = controller;

        if (controller != null) {
            // The view observes the controller.
            this.controller.register(this);
        }
    }

    /**
     * Set the controller which controls the elements of the view.
     * @param viewController The new controller which controls the elements of the view.
     */
    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }
}
