package fr.iscgroup.isc.model.connection;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import fr.iscgroup.isc.model.connection.exception.ConnectionException;
import fr.iscgroup.isc.model.connection.handler.ConnectionHandler;

/**
 * The type Socket connection.
 *
 * @author emoqu on 02/11/2020
 * @project ISC
 */
public class SocketConnection implements IConnexion {
    private String host;
    private int port, timeout;
    private Socket socket;
    protected ConnectionHandler handler;

    private DataInputStream in;
    private DataOutputStream out;

    /**
     * Instantiates a new Socket connection.
     *
     * @param host the host
     * @param port the port
     * @param timeout the timeout
     * @param handler the Connection handler
     */
    public SocketConnection(String host, int port, int timeout, ConnectionHandler handler) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.socket = new Socket();
        this.handler = handler;
    }


    @Override
    public boolean connect() {
        try {
            // set the socket & the out and in Data stream
            this.socket.connect(new InetSocketAddress(this.host, this.port), this.timeout);
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());

            this.handler.didConnect();
            return true;
        } catch (IOException exception) {
            this.handler.didDisconnect(exception);
            return false;
        }
    }

    @Override
    public boolean disconnect() {
        try {
            if(this.socket.isConnected()) {
                this.socket.close();
                this.in.close();
                this.out.close();
            }
            this.handler.didDisconnect(new ConnectionException("Socket was close properly"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean write(String data) {
        try {
            this.out.write(data.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean read() {
        try {
            byte[] bbuff = new byte[1024];
            int nbBytes = 0;
            while((nbBytes = this.in.read(bbuff, 0, 1024)) == 0){
                new Thread().sleep(100);
            }
            String res = new String(bbuff, StandardCharsets.UTF_8).substring(0, nbBytes);
            this.handler.didReceiveData(res);
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}