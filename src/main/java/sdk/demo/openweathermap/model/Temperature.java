package sdk.demo.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Temperature{

	@JsonProperty("temp")
	private double temp;

	@JsonProperty("feels_like")
	private double feelsLike;
}