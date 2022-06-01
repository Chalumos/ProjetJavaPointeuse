package fr.univtours.polytech.projet_tutore.view.timetracker.settings;

import fr.univtours.polytech.projet_tutore.model.settings.Settings;
import fr.univtours.polytech.projet_tutore.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SettingsViewController extends ViewController {

    @FXML
    private TextField ipAddressTextField;

    @FXML
    private TextField ipPortTextField;

    @FXML
    private Label errorAddressLabel;

    @FXML
    private Label errorPortLabel;

    @Override
    public SettingsView getView() {
        return (SettingsView) super.getView();
    }

    @FXML
    public void confirmSettings() {
        Settings setting = new Settings();

        try {
            setting.setIpAddress(ipAddressTextField.getText());
            errorAddressLabel.setText("");
        }
        catch (IllegalArgumentException exception){
            errorAddressLabel.setText(exception.getMessage());
        }

        try {
            setting.setIpPort(ipPortTextField.getText());
            errorPortLabel.setText("");
        }
        catch (IllegalArgumentException exception){
            errorPortLabel.setText(exception.getMessage());
        }

        if(errorAddressLabel.getText().equals("") && errorPortLabel.getText().equals("")){
            getView().getController().setSetting(setting);
            getView().close();
        }
    }

    @FXML
    public void closeSettings() {
        getView().close();
    }

    public TextField getIpPortTextField() {
        return ipPortTextField;
    }

    public void setIpPortTextField(String ipPort) {
        this.ipPortTextField.setText(ipPort);
    }

    public TextField getIpAddressTextField() {
        return ipAddressTextField;
    }

    public void setIpAddressTextField(String ipAddress) {
        this.ipAddressTextField.setText(ipAddress);
    }
}
