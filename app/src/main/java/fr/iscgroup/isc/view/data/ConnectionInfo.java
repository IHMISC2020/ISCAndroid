package fr.iscgroup.isc.view.data;

import androidx.annotation.Nullable;

/**
 * Contains all Information relative to the connection
 *
 * @author emoqu on 16/11/2020
 * @project ISC
 */
public class ConnectionInfo {
    public static final String DEFAULT_IP = "localhost";
    public static final int DEFAULT_PORT = 9000;

    public static final String EMPTY_IP = "coucou";
    public static final int EMPTY_PORT = -1;

    private String ip;
    private int port;

    /**
     * Constructor
     *
     * @param ip
     * @param port
     */
    public ConnectionInfo(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public ConnectionInfo(String ip) {
        this(ip, ConnectionInfo.EMPTY_PORT);
    }

    public ConnectionInfo(int port) {
        this(ConnectionInfo.EMPTY_IP, port);
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Set the IP
     *
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Set the port
     *
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Check if ip is fill
     *
     * @return true or false
     */
    public boolean ipIsFill() {
        return this.ip != ConnectionInfo.EMPTY_IP;
    }

    /**
     * Check if port is fill
     *
     * @return true or false
     */
    public boolean portIsFill() {
        return this.port != ConnectionInfo.EMPTY_PORT;
    }
}
