package fr.iscgroup.isc.factory.connexion;

/**
 * @author emoqu on 11/01/2021
 * @project ISC
 */
public interface IConnexionFactory {
    public void connect(String ip, int port, int timeout);
    public void sendData(String message);
    public String readData();
    public void disconnect();
}
