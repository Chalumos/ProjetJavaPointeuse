package fr.univtours.polytech.projet_tutore.model.data;

import fr.univtours.polytech.projet_tutore.model.employee.Employee;

import java.io.*;
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
    public <T> void serialize(ArrayList<T> objects) throws IOException {
        FileOutputStream file = new FileOutputStream(getFilePath());
        BufferedOutputStream buffer = new BufferedOutputStream(file);
        DataOutputStream outputStream = new DataOutputStream(buffer);
        ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);

        objectOutput.writeObject(objects);

        outputStream.close();
    }

    /**
     * Parse a file to get objects.
     */
    public <T> ArrayList<T> parse() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(getFilePath());
        BufferedInputStream buffer = new BufferedInputStream(file);
        DataInputStream outputStream = new DataInputStream(buffer);
        ObjectInputStream objectOutput = new ObjectInputStream(outputStream);

        ArrayList<T> theList = new ArrayList<T>();
        T t;
        while ((t=(T) objectOutput.readObject())!=null){
            theList.add(t);
        }

        objectOutput.readObject();
        outputStream.close();
        return theList;
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
