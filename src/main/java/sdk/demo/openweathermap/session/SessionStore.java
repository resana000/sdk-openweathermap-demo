package sdk.demo.openweathermap.session;

import sdk.demo.openweathermap.sdk_protokol.Agent;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.List;

/**
 * instead of real ones DataBase
 */
@ApplicationScope
@Component
@Data
public class SessionStore {
    private List<Agent> storage = new ArrayList<>();
}
