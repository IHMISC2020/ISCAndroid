package fr.iscgroup.isc.factory.connexion;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import fr.iscgroup.isc.model.connection.AsyncClient;
import fr.iscgroup.isc.factory.Factory;

/**
 * The type Connexion factory.
 *
 * @author emoqu on 11/01/2021
 * @project ISC
 */
public class ConnexionFactory extends Factory implements IConnexionFactory {
    /**
     * The constant instance.
     */
    public static ConnexionFactory instance;

    private Context context;
    private AsyncClient client;

    private ConnexionFactory(Context context) {
        this.setContext(context);
    }

    public boolean isConnect() {
        return this.client != null;
    }

    @Override
    public void connect(String ip, int port, int timeout) {
        this.client = new AsyncClient(ip, port, timeout);
    }

    @Override
    public void sendData(String message) {
        if(isConnect()) {
            this.client.sendDataToServer(message);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public String readData() {
        if(isConnect()) {
            this.client.readDataToServer();
            return this.client.getServerResponse();
        }
        return null;
    }

    @Override
    public void disconnect() {
        this.client.disconnect();
    }

    /**
     * GETTER & SETTER
     */

    /**
     * Gets context.
     *
     * @return the context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets context.
     *
     * @param context the context
     */
    public void setContext(Context context) {
        if(context == null)
            throw new IllegalArgumentException("Context cannot be null.");
        this.context = context;
    }

    /**
     * STATIC METHODS
     */

    /**
     * Gets {@link ConnexionFactory}.
     *
     * @param context the context
     * @return the instance
     */
    public static ConnexionFactory getInstance(Context context) {
        if(ConnexionFactory.instance == null)
            ConnexionFactory.instance = new ConnexionFactory(context);

        return ConnexionFactory.instance;
    }
}
