package fr.univtours.polytech.projet_tutore.model.socket;

import fr.univtours.polytech.projet_tutore.model.data_manager.ClockingTimeDataManager;
import fr.univtours.polytech.projet_tutore.model.data_manager.NetworkSettingsDataManager;
import fr.univtours.polytech.projet_tutore.model.settings.NetworkSettings;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    /**
     * If employee check, send a socket to the server
     */
    public Client(ArrayList<ClockingTime> clockingTimes) throws IOException {
        ClockingTimeDataManager clockingTimeDataManager = new ClockingTimeDataManager();
        NetworkSettingsDataManager networkSettingsDataManager = new NetworkSettingsDataManager();

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

        String pathClockingTime = root.getAbsolutePath() + packages + "ClockingTimeTimeTracker.txt";
        clockingTimeDataManager.setFilePath(pathClockingTime);

        try {
            ArrayList<NetworkSettings> settings = networkSettingsDataManager.parse();
            NetworkSettings networkSettings = settings.get(0);

            // If the server is not reachable : make an Exception
            Socket socket = new Socket("localhost", Integer.parseInt(networkSettings.getIpPort()));

            // Init
            InputStream is = socket.getInputStream(); // get byte.
            InputStreamReader isr = new InputStreamReader(is); // get char
            BufferedReader br = new BufferedReader(isr); // get List of char / String

            // Check if the connection with the server work
            String connectionCheck = br.readLine();
            System.out.println(connectionCheck);

            if (connectionCheck != null) {
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                // Send to the server the list of clocking-time.
                outputStream.writeObject(clockingTimes);

                // Clear the file.
                clockingTimeDataManager.serialize(new ArrayList<>()); // Serialize(write) list of clocking-times in a file.
            }
        } catch (Exception e) {
            System.out.println("Connection has failed");
            clockingTimeDataManager.serialize(clockingTimes); // Serialize(write) list of clocking-times in a file.
        }
        finally {
            // Clear the list of clocking-time which are saves until the connection was enabled.
            clockingTimes.clear();
        }
    }
}
