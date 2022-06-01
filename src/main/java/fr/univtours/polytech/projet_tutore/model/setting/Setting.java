package fr.univtours.polytech.projet_tutore.model.setting;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.regex.Pattern;

public class Setting implements Externalizable {

    private String ipAddress;
    private String ipPort;

    public Setting(){
        ipAddress = "192.168.0.0";
        ipPort = "8080";
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) throws IllegalArgumentException {
        boolean formatIP = Pattern.matches("\\d{1,3}+.\\d{1,3}+.\\d{1,3}+.\\d{1,3}+", ipAddress);
        if(formatIP) {
            this.ipAddress = ipAddress;
        }
        else {
            throw new IllegalArgumentException("Adress IP invalide");
        }
    }

    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) throws IllegalArgumentException{
        boolean formatPort = Pattern.matches("\\d{2,4}", ipPort);
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
