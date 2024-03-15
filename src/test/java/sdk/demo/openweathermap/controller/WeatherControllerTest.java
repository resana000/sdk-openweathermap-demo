package sdk.demo.openweathermap.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sdk.demo.openweathermap.model.WeatherDto;
import sdk.demo.openweathermap.sdk_protokol.Agent;
import sdk.demo.openweathermap.session.CachableWeatherService;
import sdk.demo.openweathermap.session.SessionStore;
import sdk.demo.openweathermap.session.UniquieAgentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @InjectMocks
    WeatherController weatherController;
    @Mock
    UniquieAgentService uniquieAgentService;
    @Mock
    CachableWeatherService cachableWeatherService;
    @Mock
    HttpServletRequest httpServletRequest;
    @Mock
    SessionStore sessionStore;


    @Test
    void getWeather() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        List<Agent> clients = new ArrayList<>();

        when(cachableWeatherService.getWeather("London", "api_key000")).thenReturn(
                WeatherDto.builder()
                        .name("London")
                        .datetime(new Date().getTime())
                        .build()
        );
        when(httpServletRequest.getRemoteAddr()).thenReturn("0.0.0.0");
        when(sessionStore.getStorage()).thenReturn(clients);
        when(uniquieAgentService.isUnique("api_key000")).thenReturn(true);

        WeatherDto responseEntity = weatherController.getWeather("London", "api_key000", httpServletRequest);
        assertThat(responseEntity.getName()).isEqualTo("London");
        assertThat(responseEntity.getName()).isNotEqualTo(new Date().getTime());
    }
}