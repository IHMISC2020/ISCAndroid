package fr.iscgroup.isc.model.connection;

/**
 * The interface Connexion.
 *
 * @author emoqu on 02/11/2020
 * @project ISC
 */
public interface IConnexion {
    /**
     * Connect boolean.
     *
     * @return true if connect succeed, or false if connect failed
     */
    public boolean connect();

    /**
     * Disconnect boolean.
     *
     * @return true if Disconnect succeed, or false if Disconnect failed
     */
    public boolean disconnect();

    /**
     * Write boolean.
     *
     * @param data the data
     * @return true if write succeed, or false if write failed
     */
    public boolean write(String data);

    /**
     * Read string.
     *
     * @return string from socket if read succeed, or null if read failed
     */
    public boolean read();
}
