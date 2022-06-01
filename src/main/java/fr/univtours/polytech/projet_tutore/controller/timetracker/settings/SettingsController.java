package fr.univtours.polytech.projet_tutore.controller.timetracker.settings;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.data.SettingDataManager;
import fr.univtours.polytech.projet_tutore.model.settings.Settings;

import java.util.ArrayList;

public class SettingsController extends Controller {
    private Settings setting;

    public SettingsController() {
        setting = null;
        initialize();
    }

    @Override
    public void initialize() {
        ArrayList<Settings> settings = new ArrayList<Settings>();

        SettingDataManager settingDataManager = new SettingDataManager();
        try {
            settings = settingDataManager.parseSetting();

            if (settings.size() > 0) {
                setting = settings.get(0);
            } else {
                setting = new Settings();
            }

            String[] messages = {"ip","port"};
            notifyObservers(messages);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Settings getSetting() {
        return setting;
    }

    public void setSetting(Settings newSetting) {
        setting = newSetting;

        ArrayList<Settings> settings = new ArrayList<Settings>();
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
