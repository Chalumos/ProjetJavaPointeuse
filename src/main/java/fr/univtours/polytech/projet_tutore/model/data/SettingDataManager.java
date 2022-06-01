package fr.univtours.polytech.projet_tutore.model.data;

import fr.univtours.polytech.projet_tutore.model.settings.Settings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SettingDataManager extends DataManager {

    @Override
    public void initialize() {
        // Get the project root path.
        File root = new File("");

        // Create the path for the packages.
        String packages = File.separator + "src" +
                File.separator + "main" +
                File.separator + "resources" +
                File.separator + "fr" +
                File.separator + "univtours" +
                File.separator + "polytech" +
                File.separator + "projet_tutore" +
                File.separator + "data" +
                File.separator;


        String path = root.getAbsolutePath() + packages + "Settings.txt";

        setFilePath(path);

    }

    public void serializeSetting(ArrayList<Settings> settings) throws IOException {
        super.serialize(settings);
    }

    public ArrayList<Settings> parseSetting() throws IOException, ClassNotFoundException {
        return super.parse();
    }
}
