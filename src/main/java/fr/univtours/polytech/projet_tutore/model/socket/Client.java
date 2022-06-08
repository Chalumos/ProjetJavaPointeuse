package fr.univtours.polytech.projet_tutore.model.socket;

import fr.univtours.polytech.projet_tutore.model.settings.NetworkSettings;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Send TCP sockets.
 */
public class Client {
    /**
     * Send a list of data to a server.
     * @param dataList The data list to send.
     * @param networkSettings The network settings of the server.
     * @return Whether the sending succeed or not.
     */
    public static <T> boolean sendData(ArrayList<T> dataList, NetworkSettings networkSettings) {
        boolean dataSent = false;

        try {
            // If the server is not reachable : make an Exception.
            Socket socket = new Socket(networkSettings.getIpAddress(), Integer.parseInt(networkSettings.getIpPort()));

            // Initialize the socket.
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            // Check if the connection with the server succeed.
            String connectionCheck = br.readLine();
            System.out.println(connectionCheck);

            // If the connection with the server succeed.
            if (connectionCheck != null) {
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                // Send the data list to the server.
                outputStream.writeObject(dataList);
                dataSent = true;
            }
        } catch (Exception e) {
            System.out.println("Connection has failed.");
        }

        return dataSent;
    }
}
