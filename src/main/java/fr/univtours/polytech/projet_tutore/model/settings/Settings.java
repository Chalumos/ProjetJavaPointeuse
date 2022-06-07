package fr.univtours.polytech.projet_tutore.model.settings;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.regex.Pattern;

public class Settings implements Externalizable {

    /**
     * The current ip address.
     */
    private String ipAddress;

    /**
     * The current ip port.
     */
    private String ipPort;

    /**
     * Create a setting.
     */
    public Settings() {
        ipAddress = "192.168.0.0";
        ipPort = "8080";
    }
    /**
     * Get the current ip address.
     * @return The current ip address;
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Set the current ip address of the setting.
     * @param ipAddress The new current ip address.
     */
    public void setIpAddress(String ipAddress) throws IllegalArgumentException {
        // check the format of ip address
        boolean formatIP = Pattern.matches("\\d{1,3}+.\\d{1,3}+.\\d{1,3}+.\\d{1,3}+", ipAddress);
        // if the ip address format is correct we set ip port
        // else return an error
        if(formatIP) {
            this.ipAddress = ipAddress;
        }
        else {
            throw new IllegalArgumentException("Adress IP invalide");
        }
    }

    /**
     * Get the current ip port.
     * @return The current ip port;
     */
    public String getIpPort() {
        return ipPort;
    }

    /**
     * Set the current ip port of the setting.
     * @param ipPort The new current ip port.
     */
    public void setIpPort(String ipPort) throws IllegalArgumentException{
        // check the format of ip port
        boolean formatPort = Pattern.matches("\\d{2,4}", ipPort);

        // if the ip port format is correct we set ip port
        // else return an error
        if(formatPort) {
            this.ipPort = ipPort;
        }
        else {
            throw new IllegalArgumentException("Port IP invalide");
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        if (this!=null){
            out.writeObject(ipAddress);
            out.writeObject(ipPort);
        }
        else{
            throw new IOException();
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ipAddress = (String) in.readObject();
        ipPort =(String) in.readObject();
    }
}
