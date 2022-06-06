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
        FileInputStream file = new FileInputStream(getFilePath());
        BufferedInputStream inputBuffer = new BufferedInputStream(file);
        DataInputStream inputStream = new DataInputStream(inputBuffer);
        ArrayList<T> objectList = new ArrayList<T>(1);
        T t;

        try {
            ObjectInputStream objectInput = new ObjectInputStream(inputStream);

            t = (T) objectInput.readObject();

            if (t != null) {
                objectList.addAll((ArrayList<T>) t);
            }

            inputStream.close();
        }
        catch(EOFException e){
            e.printStackTrace();
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
