package fr.univtours.polytech.projet_tutore.controller.timetracker.setting;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.data.SettingDataManager;
import fr.univtours.polytech.projet_tutore.model.setting.Setting;

import java.util.ArrayList;

/**
 * Controller to manage the setting view.
 */
public class SettingController extends Controller {

    /**
     * Setting controlled by the controller.
     */
    private Setting setting;

    /**
     * Create the controller.
     */
    public SettingController() {
        setting = new Setting();
        initialize();
    }

    /**
     * initialise the setting with the setting file.
     */
    @Override
    public void initialize() {
        ArrayList<Setting> settings = new ArrayList<Setting>();

        SettingDataManager settingDataManager = new SettingDataManager();
        try {
            settings = settingDataManager.parseSetting();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        setting.setIpAddress(settings.get(0).getIpAddress());
        setting.setIpPort(settings.get(0).getIpPort());

        String[] messages = {"ip","port"};
        notifyObservers(messages);
    }

    /**
     * Get the setting.
     * @return The setting.
     */
    public Setting getSetting() {
        return setting;
    }

    /**
     * Set the setting in the setting file.
     * @param newSetting The new setting.
     */
    public void setSetting(Setting newSetting) {
        setting = newSetting;

        ArrayList<Setting> settings = new ArrayList<Setting>();
        settings.add(setting);

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
