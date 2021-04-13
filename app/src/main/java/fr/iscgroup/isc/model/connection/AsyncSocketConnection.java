package fr.iscgroup.isc.model.connection;

import fr.iscgroup.isc.model.connection.handler.ConnectionHandler;

/**
 * @author emoqu on 18/11/2020
 * @project ISC
 */
public class AsyncSocketConnection extends SocketConnection implements Runnable {

    /**
     * Instantiates a new Async socket connection.
     *
     * @param host    the host
     * @param port    the port
     * @param timeout the timeout
     * @param handler the Connection handler
     */
    public AsyncSocketConnection(String host, int port, int timeout, ConnectionHandler handler) {
        super(host, port, timeout, handler);
    }

    @Override
    public void run() {
        this.connect();
    }
}
