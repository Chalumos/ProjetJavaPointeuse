package fr.univtours.polytech.projet_tutore.model.data;

import java.io.IOException;
import java.util.ArrayList;

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
     * Serialize an object.
     * @param objects The objects to serialize.
     */
    public abstract void serialize(ArrayList<Object> objects) throws IOException;

    /**
     * Parse a file to get objects.
     */
    public abstract ArrayList<Object> parse();

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
