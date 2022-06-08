package fr.univtours.polytech.projet_tutore.view.timetracker.settings;

import fr.univtours.polytech.projet_tutore.model.settings.NetworkSettings;
import fr.univtours.polytech.projet_tutore.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller which controls the elements of a SettingView.
 */
public class SettingsViewController extends ViewController {

    /**
     * TextField of the current ip address.
     */
    @FXML
    private TextField ipAddressTextField;

    /**
     * TextField of the current ip port.
     */
    @FXML
    private TextField ipPortTextField;

    /**
     * Label to print the address ip error.
     */
    @FXML
    private Label errorAddressLabel;

    /**
     * Label to print the ip port error.
     */
    @FXML
    private Label errorPortLabel;

    /**
     * Confirm and close the change of setting view.
     */
    @FXML
    public void confirmSettings() {
        NetworkSettings networkSettings = getView().getController().getNetworkSettings();
        boolean isOk = true;

        // Edit the IP address.
        try {
            networkSettings.setIpAddress(ipAddressTextField.getText());
            errorAddressLabel.setText("");
        }
        catch (IllegalArgumentException exception){
            errorAddressLabel.setText(exception.getMessage());
            isOk = false;
        }

        // Edit the IP port.
        try {
            networkSettings.setIpPort(ipPortTextField.getText());
            errorPortLabel.setText("");
        }
        catch (IllegalArgumentException exception){
            errorPortLabel.setText(exception.getMessage());
            isOk = false;
        }

        // If the settings are OK, close the view.
        if(isOk) {
            getView().getController().setNetworkSettings(networkSettings);
            getView().close();
        }
    }

    /**
     * Close setting view.
     */
    @FXML
    public void closeSettings() {
        getView().close();
    }

    @Override
    public SettingsView getView() {
        return (SettingsView) super.getView();
    }

    /**
     * Get the text field of the current ip port.
     * @return The text field of the current ip port.
     */
    public TextField getIpPortTextField() {
        return ipPortTextField;
    }

    /**
     * Set the ip port of the current ip port text field.
     * @param ipPort The new current ip port.
     */
    public void setIpPortTextField(String ipPort) {
        this.ipPortTextField.setText(ipPort);
    }

    /**
     * Get the text field of the current ip address.
     * @return The text field of the current ip address.
     */
    public TextField getIpAddressTextField() {
        return ipAddressTextField;
    }

    /**
     * Set the ip address of the current ip address text field.
     * @param ipAddress The new current ip address.
     */
    public void setIpAddressTextField(String ipAddress) {
        this.ipAddressTextField.setText(ipAddress);
    }
}
