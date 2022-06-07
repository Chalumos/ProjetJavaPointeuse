package fr.univtours.polytech.projet_tutore.model.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Function;

public class ServerMultiThread<T> extends Thread {
    private int PORT = 9521;

    public Function<ArrayList<T>, Void> onServerGetData;

    public ServerMultiThread(Function<ArrayList<T>, Void> onServerGetData) {
        this.onServerGetData = onServerGetData;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(PORT); // On ouvre un serveur d'écoute
            System.out.println("Server is up and running on port " + PORT);
            while (true){
                Socket s = ss.accept(); // Connecte autant de client qu'il ne passe dans la boucle
                Service service = new Service(s, onServerGetData); //
                service.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //public static void main(String[] args) {
    //    new ServerMultiThread().start(); // Créer l'objet, fait appel à la méthode start de Thread, et la méthode start va faire appel à la méthode run
    //}
}
