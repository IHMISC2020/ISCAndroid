package fr.iscgroup.isc.model.connection;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.net.ConnectException;

import fr.iscgroup.isc.model.connection.handler.SocketConnexionHandler;

/**
 * @author emoqu on 18/11/2020
 * @project ISC
 */
public class AsyncClient {
    private final String TAG = this.getClass().getName();

    private int timeout;
    private AsyncSocketConnection connection;
    private SocketConnexionHandler handler;
    private Thread lastTask;

    /**
     * Constructor that init client thread & connection
     *
     * @param host
     * @param port
     * @param timeout
     */
    public AsyncClient(String host, int port, int timeout) {
        this.handler = new SocketConnexionHandler();
        this.connection = new AsyncSocketConnection(host, port, timeout, this.handler);
        this.timeout= timeout;

        this.lastTask = new Thread(this.connection);
        this.lastTask.start();
    }

    /**
     * @return the response from the server (can be null)
     */
    public String getServerResponse() {
        try{
            this.lastTask.join();
            return this.handler.getResponse();
        }catch (InterruptedException e){
            this.handler.didDisconnect(e);
        }
        return "";
    }

    /**
     * Disconnect the client
     * @return true if client is disconnect, false if it isn't
     */
    public boolean disconnect() {
        try {
            // Wait end of lastTask thread
            this.lastTask.join();

            // Try to send data
            if(this.handler.isConnected()) {
                // Reset the last task & run it
                this.lastTask = new Thread(() -> this.connection.disconnect());
                this.lastTask.start();
                return true;
            }else
                this.handler.didDisconnect(new ConnectException());
        } catch (InterruptedException e) {
            this.handler.didDisconnect(e);
        }
        return false;
    }

    /**
     * Send data to the server
     * @param data
     * @return true if data was send to the server, false if it isn't
     */
    public boolean sendDataToServer(String data) {
        try {
            // Wait end of lastTask thread
            this.lastTask.join();

            // Try to send data
            if(this.handler.isConnected()) {
                // Reset the last task & run it
                this.lastTask = new Thread(() -> this.connection.write(data));
                this.lastTask.start();
                return true;
            }else
                this.handler.didDisconnect(new ConnectException());
        } catch (InterruptedException e) {
            this.handler.didDisconnect(e);
        }
        return false;
    }

    /**
     * Read data from server & stock it into handler
     * @return true if data can be read from server, false if it isn't be read
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean readDataToServer() {
        try {
            // Wait end of lastTask thread
            this.lastTask.join();

            // Try to send data
            if(this.handler.isConnected()) {
                // Reset the last task & run it
                this.lastTask = new Thread(() -> this.connection.read());
                this.lastTask.start();
                return true;
            }else
                this.handler.didDisconnect(new ConnectException());
        } catch (InterruptedException e) {
            this.handler.didDisconnect(e);
        }
        return false;
    }
}
