package fr.univtours.polytech.projet_tutore.model.data_manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class NetworkSettingDataManagerTest {

    private NetworkSettingsDataManager networkSettingsDataManager;

    @BeforeEach
    void setUp(){
        networkSettingsDataManager = new NetworkSettingsDataManager();
    }

    @Test
    void testSerialize() {
        ArrayList<NetworkSettingsDataManager> networkSettingsDataManagerList = new ArrayList<NetworkSettingsDataManager>();
        ArrayList<NetworkSettingsDataManager> newNetworkSettingsDataManagerList = new ArrayList<NetworkSettingsDataManager>();

        String path = (new File(networkSettingsDataManager.getFilePath())).getParentFile().getParentFile().getPath();
        path += File.separator + "data_test" + File.separator + "NetworkSettings.txt";

        try {
            networkSettingsDataManager.setFilePath(path);
            networkSettingsDataManager.serialize(networkSettingsDataManagerList);

            newNetworkSettingsDataManagerList.addAll(networkSettingsDataManager.parse());

            for (int i = 0; i < networkSettingsDataManagerList.size(); i++) {
                NetworkSettingsDataManager networkSettingsDataManagerElement = networkSettingsDataManagerList.get(i);
                NetworkSettingsDataManager newNetworkSettingsDataManagerElement = newNetworkSettingsDataManagerList.get(i);

                Assertions.assertEquals(networkSettingsDataManagerElement.toString(), newNetworkSettingsDataManagerElement.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
}
