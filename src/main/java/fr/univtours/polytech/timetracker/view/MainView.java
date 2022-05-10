package fr.univtours.polytech.timetracker.view;

import fr.univtours.polytech.timetracker.controller.MainController;
import fr.univtours.polytech.timetracker.controller.Observable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainView extends Application implements Observer {
    private MainController controller;

    public MainView() {
        controller = null;
    }

    public MainView(MainController controller) {
        setController(controller);
    }

    public void show() {
        MainView.launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // In the case of the separator for the resources, it's the same for every OS: '/'.
        // So there is no need to use File.separator.
        String fileName = "/fr/univtours/polytech/timetracker/mainView.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource(fileName));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Time tracker emulator");
        stage.setScene(scene);

        MainController controller = fxmlLoader.getController();
        // TODO: Modifier les attributs "model" du controleur
        setController(controller);

        stage.show();
    }

    @Override
    public void update(Observable observable) {
        // TODO: Mettre à jour les champs de la vue à partir des données du contrôleur
    }

    public MainController getController() {
        return controller;
    }

    public void setController(MainController controller) {
        this.controller = controller;
        this.controller.register(this);
        update(this.controller);
    }
}
