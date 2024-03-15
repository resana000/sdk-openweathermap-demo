package sdk.demo.openweathermap.session;

import dev.coly.weather.UnitType;
import dev.coly.weather.WeatherClient;
import dev.coly.weather.WeatherState;
import dev.coly.weather.apis.CurrentWeather;
import lombok.Data;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;
import sdk.demo.openweathermap.model.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScope
@Repository
@Data
public class CachableWeatherService {

    private Map<String, WeatherDto> cachedWeather = new HashMap<>();

    /**
     Used by controller and read already 10 minuts cached weather
     */
    @Cacheable("cachedWeather")
    public WeatherDto getWeather(String city, String apiKey) throws IOException {
        callOpenWeather(city, apiKey);
        return cachedWeather.get(city);
    }

    /**
     * Get weather and cached every 10 minuts
     * @param city
     * @param apiKey
     */
    @CacheEvict(value = "cachedWeather", allEntries = true)
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void callOpenWeather(String city, String apiKey) throws IOException {
        WeatherClient client = new WeatherClient(apiKey);
        CurrentWeather weather = client.getCurrentWeatherByCityName(city, UnitType.Metric, "EN");
        WeatherState current = weather.getWeatherState();
        WeatherDto weatherDto = WeatherDto.builder()
                .weather(new Weather(current.getWeatherDescription(), current.getWeather()))
                .temperature(new Temperature(current.getTemperature(), current.getTemperature()))
                .wind(new Wind(current.getWindSpeed()))
                .datetime(weather.getTimeOfData())
                .sys(new Sys(weather.getStation().getSunrise(), weather.getStation().getSunset()))
                .timezone(weather.getStation().getId())
                .name(weather.getStation().getName())
                .build();
        cachedWeather.put(apiKey, weatherDto);
    }
}
