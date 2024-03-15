package sdk.demo.openweathermap.sdk_protokol;

import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.UUID;

/**
 * Current api_key client
 * always unique UUID event if IP`s match
 */

@Data
public class Agent {
    @NonNull
    private UUID Uuid;
    private String ip;
    private String apiKey;

    public Agent(String ip) {
        this.ip = ip;
        this.Uuid = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Uuid.equals(agent.Uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Uuid);
    }
}
