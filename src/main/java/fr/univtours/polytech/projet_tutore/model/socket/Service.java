package fr.univtours.polytech.projet_tutore.model.socket;

import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;

import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Service extends Thread{
    private Socket socket; // Doit communiquer avec un client, donc il communique via la socket

    public Service(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Init
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            pw.println("Connection with the server check");


            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            ArrayList<ClockingTime> recvClockingTimeList = (ArrayList<ClockingTime>) inputStream.readObject();
            if (recvClockingTimeList != null){

            }

            for (ClockingTime clockingTime : recvClockingTimeList) {
                System.out.println(clockingTime.toString());
                if (clockingTime != null) {
                    outputStream.writeObject(clockingTime);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
