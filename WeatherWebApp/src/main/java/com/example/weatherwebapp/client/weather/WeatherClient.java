package com.example.weatherwebapp.client.weather;

import com.example.weatherwebapp.client.weather.dto.forecast.TomorrowForecastDTO;
import com.example.weatherwebapp.client.weather.dto.realtime.TomorrowRealTimeWeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class WeatherClient {
    private final WebClient webClient;

    @Value("${weather.api.key}")
    private String WEATHER_API_KEY;

    @Autowired
    public WeatherClient (WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.tomorrow.io").build();
    }

    public TomorrowRealTimeWeatherDTO getRealTimeWeather(String location) {
        return callGetMethod("/v4/weather/realtime", TomorrowRealTimeWeatherDTO.class, Map.of(
                "location", location,
                "units", "metric"
        ));
    }

    public TomorrowRealTimeWeatherDTO getRealTimeWeather(double latitude, double longitude) {
        return callGetMethod("/v4/weather/realtime", TomorrowRealTimeWeatherDTO.class, Map.of(
                "location", latitude + ", " + longitude,
                "units", "metric"
        ));
    }

    public TomorrowForecastDTO getWeatherForecast(String location) {
        return callGetMethod("/v4/weather/forecast", TomorrowForecastDTO.class, Map.of(
                "location", location,
                "timesteps", "1d",
                "units", "metric"
        ));
    }

    private <T> T callGetMethod(String path, Class<T> responseType, Map<String, Object> queryParams) {
        return webClient.get()
                .uri(uriBuilder -> {
                    var builder = uriBuilder
                            .path(path);
                    queryParams.forEach(builder::queryParam);
                    builder.queryParam("apikey", WEATHER_API_KEY);
                    return builder.build();
                })
                .headers(headers -> {
                    headers.add("Accept", "application/json");
                    headers.add("accept-encoding", "deflate, gzip, br");
                })
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }
}
