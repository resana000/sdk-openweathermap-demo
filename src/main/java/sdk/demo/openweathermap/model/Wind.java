package sdk.demo.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Wind{

	@JsonProperty("speed")
	private Object speed;
}