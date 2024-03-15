package sdk.demo.openweathermap.sdk_protokol;

/**
 * Throw if API_LEY session canceling failed, and means there's only one left client
 */
public class ClientSessionCancelException extends RuntimeException {
    public ClientSessionCancelException(String message) {
        super(message);
    }
}
