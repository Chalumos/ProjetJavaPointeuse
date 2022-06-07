package fr.univtours.polytech.projet_tutore.controller.timetracker.settings;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.data_manager.NetworkSettingsDataManager;
import fr.univtours.polytech.projet_tutore.model.settings.NetworkSettings;

import java.util.ArrayList;

/**
 * Controller to manage the settings view.
 */
public class SettingsController extends Controller {
    /**
     * Network settings controlled by the controller.
     */
    private NetworkSettings networkSettings;

    /**
     * Initialize the network settings with the localhost address.
     */
    public SettingsController() {
        networkSettings = new NetworkSettings();
    }

    /**
     * initialise the settings with the settings file.
     */
    @Override
    public void initialize() {
        NetworkSettingsDataManager networkSettingsDataManager = new NetworkSettingsDataManager();

        try {
            ArrayList<NetworkSettings> settings = networkSettingsDataManager.parse();

            // If settings were serialized.
            if (settings.size() > 0) {
                networkSettings = settings.get(0);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        String[] messages = {"address", "port"};
        notifyObservers(messages);
    }

    /**
     * Get the setting.
     * @return The setting.
     */
    public NetworkSettings getNetworkSettings() {
        return networkSettings;
    }

    /**
     * Set the setting in the setting file.
     * @param newSettings The new setting.
     */
    public void setNetworkSettings(NetworkSettings newSettings) {
        networkSettings = newSettings;

        // Write the new settings in a data file.
        ArrayList<NetworkSettings> settings = new ArrayList<>();
        settings.add(getNetworkSettings());

        NetworkSettingsDataManager networkSettingsDataManager = new NetworkSettingsDataManager();
        try {
            networkSettingsDataManager.serialize(settings);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        String[] messages = {"address", "port"};
        notifyObservers(messages);
    }
}
