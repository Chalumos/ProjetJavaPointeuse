package fr.univtours.polytech.projet_tutore.model.data;

import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

    public void serializeClockingTime(ArrayList<ClockingTime> clockingTime) throws IOException {
        super.serialize(clockingTime);
    }

    public ArrayList<ClockingTime> parseClockingTime() throws IOException, ClassNotFoundException {
        return super.parse();
    }

}
