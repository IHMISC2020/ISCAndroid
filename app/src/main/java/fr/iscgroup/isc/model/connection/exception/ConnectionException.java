package fr.iscgroup.isc.model.connection.exception;

/**
 * Connection error
 *
 * @author emoqu on 18/11/2020
 * @project ISC
 */
public class ConnectionException extends Exception {
    /**
     * Default error message
     */
    private static final String ERROR_MESSAGE = "An error occurred when attempt to connect to the socket";

    /**
     * Constructor which use custom error message
     * @param error
     */
    public ConnectionException(String error) {
        super(error);
    }

    /**
     * Constructor which use default message
     */
    public ConnectionException() {
        this(ERROR_MESSAGE);
    }
}
