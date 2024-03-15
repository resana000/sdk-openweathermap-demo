package sdk.demo.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sys{

	@JsonProperty("sunrise")
	private long sunrise;

	@JsonProperty("sunset")
	private long sunset;
}