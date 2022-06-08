package fr.univtours.polytech.projet_tutore.model.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Function;

public class Service<T> extends Thread{
    /**
     * Socket of the service.
     */
    private Socket socket;

    /**
     * Initialize a service with the current socket.
     * @param socket The current socket.
     * @param onServerGetData Function called when the server get data.
     */
    public Service(Socket socket, Function<ArrayList<T>, Void> onServerGetData) {
        super();
        this.socket = socket;
        this.onServerGetData = onServerGetData;
    }

    /**
     * Function called when the server get data.
     */
    public Function<ArrayList<T>, Void> onServerGetData;

    @Override
    public void run() {
        try {
            // Init for send String.
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            // Send to the client that the connection with the server work.
            pw.println("Connection with the server check");

            // Init for send Object.
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Receive the list of data from the client.
            ArrayList<T> data = (ArrayList<T>) inputStream.readObject();

            if (data != null){
                // Send the data list to the application.
                onServerGetData.apply(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
