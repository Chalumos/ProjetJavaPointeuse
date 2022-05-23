package fr.univtours.polytech.projet_tutore.model.data;

import java.io.*;
import java.util.ArrayList;

public class EmployeeDataManager extends DataManager {
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


        String path = root.getAbsolutePath() + packages + "Employee.txt";

        setFilePath(path);
    }

    @Override
    public void serialize(ArrayList<Object> objects) throws IOException {
        FileOutputStream file = new FileOutputStream(getFilePath());
        BufferedOutputStream buffer = new BufferedOutputStream(file);
        DataOutputStream outputStream = new DataOutputStream(buffer);

        /* TODO: trouver un moyen de sérialiser un Objet de la classe Employee !
            pour cela il faut que Employee implement Externalizable */
        // dataEmployee.writeUTF("");

        outputStream.close();
    }

    @Override
    public ArrayList<Object> parse() {
        return null;
    }
}
