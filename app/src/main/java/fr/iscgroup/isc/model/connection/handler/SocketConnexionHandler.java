package fr.iscgroup.isc.model.connection.handler;

/**
 * @author emoqu on 17/11/2020
 * @project ISC
 */
public class SocketConnexionHandler implements ConnectionHandler {
    private boolean isConnected;
    private String receiveData;
    private Exception error;

    /**
     * Constructor that init stat (disconnected)
     */
    public SocketConnexionHandler() {
        this.isConnected = false;
    }

    @Override
    public void didReceiveData(String data) {
        this.receiveData = data;
    }

    @Override
    public void didDisconnect(Exception error) {
        this.error = error;
        this.isConnected = false;
    }

    @Override
    public void didConnect() {
        this.isConnected = true;
    }

    /**
     * @return boolean which indicates if client is connected or not
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * @return the exception throw by the client
     */
    public Exception getException() { return this.error; }

    /**
     * @return the response receive by the server
     */
    public String getResponse() {
        return this.receiveData;
    }
}
