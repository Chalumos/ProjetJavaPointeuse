package fr.univtours.polytech.projet_tutore.model.data_manager;

import java.io.File;

public class ClockingTimeDataManager extends DataManager{
    public ClockingTimeDataManager(){super();}

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

        String path = root.getAbsolutePath() + packages + "ClockingTime.txt";
        setFilePath(path);
    }
}
