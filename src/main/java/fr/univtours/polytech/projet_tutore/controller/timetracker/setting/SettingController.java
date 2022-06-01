package fr.univtours.polytech.projet_tutore.controller.timetracker.setting;

import fr.univtours.polytech.projet_tutore.controller.Controller;
import fr.univtours.polytech.projet_tutore.model.data.SettingDataManager;
import fr.univtours.polytech.projet_tutore.model.setting.Setting;

import java.util.ArrayList;

public class SettingController extends Controller {

    private Setting setting;

    public SettingController() {
        setting = new Setting();
        initialize();
    }

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

    public Setting getSetting() {
        return setting;
    }

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
