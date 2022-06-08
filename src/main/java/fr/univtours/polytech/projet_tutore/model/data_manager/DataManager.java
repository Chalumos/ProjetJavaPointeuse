package fr.univtours.polytech.projet_tutore.model.data_manager;

import java.io.*;
import java.util.ArrayList;

/**
 * Allows to serialize a model class.
 */
public abstract class DataManager {
    /**
     * The path of the file in which serialize or parse the objects.
     */
    private String filePath;

    /**
     * Create the data manager.
     */
    public DataManager() {
        initialize();
    }

    /**
     * Initialize the file path.
     */
    public abstract void initialize();

    /**
     * Serialize objects.
     * @param objects The objects to serialize.
     */
    public <T> void serialize(ArrayList<T> objects) throws IOException {
        File file = new File(getFilePath());

        // Create the folder if it doesn't exist.
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }

        // Create the file if it doesn't exist.
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream outputFile = new FileOutputStream(getFilePath());
        BufferedOutputStream outputBuffer = new BufferedOutputStream(outputFile);
        DataOutputStream outputStream = new DataOutputStream(outputBuffer);
        ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);

        objectOutput.writeObject(objects);

        outputStream.close();
    }

    /**
     * Parse a file to get objects.
     */
    public <T> ArrayList<T> parse() throws IOException, ClassNotFoundException {
        File file = new File(getFilePath());
        ArrayList<T> objectList = new ArrayList<T>();

        // Check that the file exists to parse it.
        if (file.exists() && file.length() > 0) {
            FileInputStream inputFile = new FileInputStream(file.getPath());
            BufferedInputStream inputBuffer = new BufferedInputStream(inputFile);
            DataInputStream inputStream = new DataInputStream(inputBuffer);

            try {
                ObjectInputStream objectInput = new ObjectInputStream(inputStream);

                ArrayList<T> list = (ArrayList<T>) objectInput.readObject();

                if (list != null) {
                    objectList.addAll(list);
                }

                inputStream.close();
            } catch (EOFException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The file '" + filePath + "' doesn't exist.");
        }

        return objectList;
    }

    /**
     * Get the path of the file in which serialize or parse the objects.
     * @return The file path.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Set the path of the file in which serialize or parse the objects.
     * @param newFilePath The new file path.
     */
    public void setFilePath(String newFilePath) {
        filePath = newFilePath;
    }
}
