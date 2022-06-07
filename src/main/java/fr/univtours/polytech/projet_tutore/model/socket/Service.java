package fr.univtours.polytech.projet_tutore.model.socket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Function;

public class Service<T> extends Thread{
    private Socket socket; // Doit communiquer avec un client, donc il communique via la socket

    public Service(Socket socket, Function<ArrayList<T>, Void> onServerGetData) {
        super();
        this.socket = socket;
        this.onServerGetData = onServerGetData;
    }

    public Function<ArrayList<T>, Void> onServerGetData;

    @Override
    public void run() {
        try {
            // Init
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            // Send to the client that the connection with the server work
            pw.println("Connection with the server check");

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Receive the list of clocking-time for the client
            ArrayList<T> data = (ArrayList<T>) inputStream.readObject();

            for (T singleData : data) {
                System.out.println(singleData);
            }

            if (data != null){
                // TODO Maybe send message to the client to confirm the reception.

                // Send the clocking-time list to the application.
                onServerGetData.apply(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
