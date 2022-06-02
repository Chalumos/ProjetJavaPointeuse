package fr.univtours.polytech.projet_tutore.model.socket;

import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private static int PORT = 9521;

    /**
     * If employee check, send a socket to the server
     */
    public Client(ArrayList<ClockingTime> clockingTimes) {
        try {
            Socket socket = new Socket("localhost", PORT);
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

                // Clear the list of clocking-time which are saves until the connection was enabled.
                clockingTimes.clear();
            }

        } catch (Exception e) {
            System.out.println("Connection has failed");
            e.printStackTrace();
        }
    }
}
