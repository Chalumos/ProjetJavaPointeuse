package fr.univtours.polytech.projet_tutore.model.data_manager;

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

    @Override
    public <T> ArrayList<T> parse() throws IOException, ClassNotFoundException {
        ArrayList<T> objectList= super.parse();
        boolean typeok=true;
        for(int i=0; i< objectList.size();i++){
            if(objectList.get(i).getClass()!=ClockingTime.class){
                typeok=false;
            }
        }
        if(typeok){
            return objectList;
        }
        else{
            throw new IOException();
        }

    }
}
