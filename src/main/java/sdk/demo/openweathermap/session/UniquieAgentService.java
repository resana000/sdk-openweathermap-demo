package sdk.demo.openweathermap.session;

import sdk.demo.openweathermap.sdk_protokol.Agent;
import sdk.demo.openweathermap.sdk_protokol.ClientSessionCancelException;
import sdk.demo.openweathermap.sdk_protokol.CurrentAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * Implements SDKs protokol
 */
@ApplicationScope
@Service
@Slf4j
public class UniquieAgentService implements CurrentAgent {

    @Autowired
    SessionStore sessionStore;

    @Override
    public boolean isUnique(String apiKey) {
        return sessionStore.getStorage().stream()
                .filter(agent -> agent.getApiKey().equals(apiKey)).count() <= 1;
    }

    @Override
    public void cancelSession(String apiKey) {
        Agent agent = sessionStore.getStorage().stream()
                .filter(a -> a.getApiKey().equals(apiKey)).findAny()
                .orElseThrow(() -> new ClientSessionCancelException("Not find session with API_KEY: " + apiKey));
        sessionStore.getStorage().remove(agent);
        log.info("Unique client with key:{} successfully removed", apiKey);
    }
}
