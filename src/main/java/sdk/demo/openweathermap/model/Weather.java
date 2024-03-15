package sdk.demo.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Weather{

	@JsonProperty("description")
	private String description;

	@JsonProperty("main")
	private String main;
}