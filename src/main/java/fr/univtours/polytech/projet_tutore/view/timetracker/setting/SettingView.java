package fr.univtours.polytech.projet_tutore.view.timetracker.setting;

import fr.univtours.polytech.projet_tutore.controller.timetracker.setting.SettingController;
import fr.univtours.polytech.projet_tutore.controller.Observable;
import fr.univtours.polytech.projet_tutore.view.View;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * View of the settings.
 */
public class SettingView extends View {

   private Stage settingsStage;

    /**
     * Initialize the controller to null.
     */
    public SettingView() {
        setController(null);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void show() {

        Platform.setImplicitExit(false);
        Platform.runLater(()->{
            try {
                settingsStage = new Stage();
                start(settingsStage);
                Platform.setImplicitExit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void start(Stage stage) throws IOException {
        // In the case of the separator for the resources, it's the same for every OS: '/'.
        // So there is no need to use File.separator.
        String fileName = "/fr/univtours/polytech/projet_tutore/view/timetracker/setting/settingsView.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(SettingView.class.getResource(fileName));

        // Creation of the scene.
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Settings");
        stage.setResizable(false);
        stage.setScene(scene);

        // Get the controller which controls the elements of the view.
        setViewController(fxmlLoader.getController());
        getViewController().setView(this);

        // Get the controller of the view.
        SettingController controller = new SettingController();
        setController(controller);
        getController().initialize();

        // Show the view.
        stage.show();
    }

    @Override
    public void update(Observable observable, String[] messages) {
        try {
            for (String message : messages) {
                switch (message) {
                    case "ip" -> {
                        String ip = getController().getSetting().getIpAddress();
                        getViewController().setIpAddressTextField(ip);
                    }
                    case "port" -> {
                        String port = getController().getSetting().getIpPort();
                        getViewController().setIpPortTextField(port);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close () {
        settingsStage.close();

    }

    @Override
    public SettingViewController getViewController() {
        return (SettingViewController) super.getViewController();
    }

    @Override
    public SettingController getController() {
        return (SettingController) super.getController();
    }
}
