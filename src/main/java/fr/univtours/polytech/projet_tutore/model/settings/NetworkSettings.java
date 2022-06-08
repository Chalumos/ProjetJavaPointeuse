package fr.univtours.polytech.projet_tutore.model.settings;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.regex.Pattern;

/**
 * Network settings.
 */
public class NetworkSettings implements Externalizable {
    /**
     * The current IP address.
     */
    private String ipAddress;

    /**
     * The current IP port.
     */
    private String ipPort;

    /**
     * Initialize the attributes with the localhost address 127.0.0.1:80.
     */
    public NetworkSettings() {
        ipAddress = "127.0.0.1";
        ipPort = "80";
    }

    /**
     * Initialize the attributes with the arguments.
     */
    public NetworkSettings(String ipAddress, String ipPort) {
        setIpAddress(ipAddress);
        setIpPort(ipPort);
    }

    /**
     * Get the IP address.
     *
     * @return The IP address;
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Set the IP address.
     *
     * @param ipAddress The new IP address.
     */
    public void setIpAddress(String ipAddress) throws IllegalArgumentException {
        // Check the format of the IP address.
        boolean formatIP = Pattern.matches("\\d{1,3}+.\\d{1,3}+.\\d{1,3}+.\\d{1,3}+", ipAddress);

        // If the IP address format is correct.
        if (formatIP) {
            // Split the IP address to each .
            String[] checkIpAdress = ipAddress.split("\\.");
            // Checks if each part of the IP address is valid
            for (String ipAddressPart : checkIpAdress) {
                int checkIpAddressNumber = Integer.parseInt(ipAddressPart);
                if (checkIpAddressNumber > 255) {
                    throw new IllegalArgumentException("Invalid IP address");
                }
            }
            this.ipAddress = ipAddress;
        } else {
            throw new IllegalArgumentException("Invalid IP address");
        }
    }

    /**
     * Get the IP port.
     *
     * @return The IP port;
     */
    public String getIpPort() {
        return ipPort;
    }

    /**
     * Set the IP port.
     *
     * @param ipPort The new IP port.
     */
    public void setIpPort(String ipPort) throws IllegalArgumentException {
        // Check the format of the IP port.
        boolean formatPort = Pattern.matches("\\d{1,5}", ipPort);

        // If the IP port format is correct.
        if (formatPort) {
            // Checks if the IP port is valid
            int checkIpPort = Integer.parseInt(ipPort);
            if ((checkIpPort < 1) || (checkIpPort > 65535)) {
                throw new IllegalArgumentException("Invalid IP port");
            }

            this.ipPort = ipPort;
        } else {
            throw new IllegalArgumentException("Invalid IP port.");
        }
    }

    @Override
    public String toString() {
        return getIpAddress() + ":" + getIpPort();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(ipAddress);
        out.writeObject(ipPort);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setIpAddress((String) in.readObject());
        setIpPort((String) in.readObject());
    }
}
