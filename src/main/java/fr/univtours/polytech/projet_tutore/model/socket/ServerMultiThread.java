package fr.univtours.polytech.projet_tutore.model.socket;

import fr.univtours.polytech.projet_tutore.model.settings.NetworkSettings;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Multithreaded server.
 */
public class ServerMultiThread<T> extends Thread {
    /**
     * Network settings of the server.
     */
    private NetworkSettings networkSettings;

    /**
     * Whether the server is on or not.
     */
    private Boolean isServerOn;

    /**
     * Function called when the server get data.
     */
    public Function<ArrayList<T>, Void> onServerGetData;

    /**
     * Initialize the server with the address 127.0.0.1:9521.
     * @param onServerGetData Function called when the server get data.
     */
    public ServerMultiThread(Function<ArrayList<T>, Void> onServerGetData) {
        networkSettings = new NetworkSettings("127.0.0.1", "9521");
        this.onServerGetData = onServerGetData;
    }

    /**
     * Initialize the server with the network settings passed in argument.
     * @param onServerGetData Function called when the server get data.
     */
    public ServerMultiThread(Function<ArrayList<T>, Void> onServerGetData, NetworkSettings networkSettings) {
        this.networkSettings = networkSettings;
        this.onServerGetData = onServerGetData;
    }

    @Override
    public void run() {
        try {
            // Launch the server.
            ServerSocket ss = new ServerSocket(Integer.parseInt(networkSettings.getIpPort()));
            setServerOn(true);
            System.out.println("Server is launched and running on port " + networkSettings.getIpPort());

            // Wait TCP sockets.
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
     * Get the network settings of the server.
     * @return The network settings of the server.
     */
    public NetworkSettings getNetworkSettings() {
        return networkSettings;
    }

    /**
     * Set the network settings of the server.
     * @param networkSettings The new network settings.
     */
    public void setNetworkSettings(NetworkSettings networkSettings) {
        this.networkSettings = networkSettings;
    }

    /**
     * Whether the server launched on or not.
     * @return Whether the server is launched or not.
     */
    public Boolean isLaunched() {
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
