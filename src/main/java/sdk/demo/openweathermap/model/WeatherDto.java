package sdk.demo.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherDto {

	@JsonProperty("datetime")
	private long datetime;

	@JsonProperty("visibility")
	private int visibility;

	@JsonProperty("timezone")
	private int timezone;

	@JsonProperty("weather")
	private Weather weather;

	@JsonProperty("temperature")
	private Temperature temperature;

	@JsonProperty("name")
	private String name;

	@JsonProperty("sys")
	private Sys sys;

	@JsonProperty("wind")
	private Wind wind;
}