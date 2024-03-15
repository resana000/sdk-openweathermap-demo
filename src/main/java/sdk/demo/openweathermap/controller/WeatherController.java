package sdk.demo.openweathermap.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sdk.demo.openweathermap.model.WeatherDto;
import sdk.demo.openweathermap.sdk_protokol.Agent;
import sdk.demo.openweathermap.sdk_protokol.ConcurrentWeatherClientException;
import sdk.demo.openweathermap.session.CachableWeatherService;
import sdk.demo.openweathermap.session.SessionStore;
import sdk.demo.openweathermap.session.UniquieAgentService;

import java.io.IOException;

@RestController
public class WeatherController {

    @Autowired
    UniquieAgentService uniquieAgentService;

    @Autowired
    SessionStore sessionStore;

    @Autowired
    CachableWeatherService cachableWeatherService;

    /**
     * Cancelate client session by API_KEY
     * @param city
     * @param apiKey
     */
    @GetMapping("/current/cancel-session/{apiKey}")
    public void cancelClientSession(@PathVariable String city, @PathVariable String apiKey, HttpServletRequest request) throws IOException {
        uniquieAgentService.cancelSession(apiKey);
    }

    /**
    main endpoint for calling the weather,
    returns an ConcurrentWeatherClientException
    if there is already a client with the same key
     * @param city
     * @param apiKey
     * @return
     * @throws ConcurrentWeatherClientException, IOException
     */

    @GetMapping("/current/{city}/{apiKey}")
    public WeatherDto getWeather(@PathVariable String city, @PathVariable String apiKey, HttpServletRequest request) throws IOException {
        sessionStore.getStorage().add(new Agent(request.getRemoteAddr()));
        if (uniquieAgentService.isUnique(apiKey) == Boolean.FALSE) {
            throw new ConcurrentWeatherClientException(
                    "Only unique client with unique API_KEY can get weather");
        }
        return cachableWeatherService.getWeather(city, apiKey);
    }


}
