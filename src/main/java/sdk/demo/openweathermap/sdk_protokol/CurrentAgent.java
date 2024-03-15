package sdk.demo.openweathermap.sdk_protokol;

/**
 * sdk`s rules for concurency API_KEYs
 */
public interface CurrentAgent {
    boolean isUnique(String apiKey);
    void cancelSession(String apiKey) throws ClientSessionCancelException;
}
