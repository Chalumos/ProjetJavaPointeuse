package fr.univtours.polytech.projet_tutore.model.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Function;

public class ServerMultiThread<T> extends Thread {
    private int PORT = 9521;

    private Boolean isServerOn;

    public Function<ArrayList<T>, Void> onServerGetData;

    public ServerMultiThread(Function<ArrayList<T>, Void> onServerGetData) {
        this.onServerGetData = onServerGetData;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(PORT); // Open a server
            System.out.println("Server is up and running on port " + PORT);
            setServerOn(true);
            while (isServerOn){
                Socket s = ss.accept(); // Wait a request from a client
                Service service = new Service(s, onServerGetData);
                service.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return if the server is on or not.
     * @return if the server is on or not.
     */
    public Boolean getServerOn() {
        return isServerOn;
    }

    /**
     * Set server status.
     * @param serverOn The status of the server.
     */
    public void setServerOn(Boolean serverOn) {
        isServerOn = serverOn;
    }
}
