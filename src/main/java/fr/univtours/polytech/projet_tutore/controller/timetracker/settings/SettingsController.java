package fr.univtours.polytech.projet_tutore.controller.timetracker.settings;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.data.SettingDataManager;
import fr.univtours.polytech.projet_tutore.model.settings.Settings;

import java.util.ArrayList;

public class SettingsController extends Controller {
    /**
     * Setting controlled by the controller.
     */
    private Settings settings;

    /**
     * Controller to manage the setting view.
     */
    public SettingsController() {
        settings = null;
        initialize();
    }

    /**
     * initialise the setting with the setting file.
     */
    @Override
    public void initialize() {
        ArrayList<Settings> settings = new ArrayList<Settings>();

        SettingDataManager settingDataManager = new SettingDataManager();
        try {
            settings = settingDataManager.parseSetting();

            if (settings.size() > 0) {
                this.settings = settings.get(0);
            } else {
                this.settings = new Settings();
            }

            String[] messages = {"ip","port"};
            notifyObservers(messages);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Get the setting.
     * @return The setting.
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Set the setting in the setting file.
     * @param newSetting The new setting.
     */
    public void setSettings(Settings newSetting) {
        settings = newSetting;

        ArrayList<Settings> settings = new ArrayList<Settings>();
        settings.add(this.settings);

        SettingDataManager settingDataManager = new SettingDataManager();
        try {
            settingDataManager.serializeSetting(settings);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        String[] messages = {"ip","port"};
        notifyObservers(messages);
    }
}
