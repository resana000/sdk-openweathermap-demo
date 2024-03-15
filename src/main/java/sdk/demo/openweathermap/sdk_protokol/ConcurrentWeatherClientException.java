package sdk.demo.openweathermap.sdk_protokol;

/**
 * Means API_KEY already using another client
 */
public class ConcurrentWeatherClientException extends RuntimeException {
    public ConcurrentWeatherClientException(String message) {
        super(message);
    }
}
