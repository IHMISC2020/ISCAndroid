package fr.iscgroup.isc.model.connection.handler;

/**
 * Interface to Implement to handle connection errors & data
 *
 * @author emoqu on 17/11/2020
 * @project ISC
 */
public interface ConnectionHandler {
    /**
     * Notify data Reception
     *
     * @param data
     */
    public void didReceiveData(String data);

    /**
     * Notify disconnection
     *
     * @param error
     */
    public void didDisconnect(Exception error);

    /**
     * Notify Connection
     */
    public void didConnect();
}
