package com.example.weatherwebapp.client.weather;

import com.example.weatherwebapp.client.geocode.dto.ForwardGeocodeDTO;
import com.example.weatherwebapp.client.weather.dto.TomorrowRealTimeWeatherDTO;
import com.example.weatherwebapp.model.Coordinates;
import com.example.weatherwebapp.service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class WeatherClient {
    private final WebClient webClient;
    private final GeocodingService geocodingService;

    @Value("${weather.api.key}")
    private String WEATHER_API_KEY;

    @Autowired
    public WeatherClient (WebClient.Builder builder, GeocodingService geocodingService) {
        this.webClient = builder.baseUrl("https://api.tomorrow.io").build();
        this.geocodingService = geocodingService;
    }

    public TomorrowRealTimeWeatherDTO getRealTimeWeather(String location) {
        Coordinates coordinates = extractCoordinates(geocodingService.forwardGeocodingRequest(location));

        return callGetMethod("/v4/weather/realtime", TomorrowRealTimeWeatherDTO.class, Map.of(
                "location", coordinates.latitude() + ", " + coordinates.longitude(),
                "units", "metric"
        ));
    }

    public TomorrowRealTimeWeatherDTO getRealTimeWeather(double latitude, double longitude) {
        return callGetMethod("/v4/weather/realtime", TomorrowRealTimeWeatherDTO.class, Map.of(
                "location", latitude + ", " + longitude,
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

    private Coordinates extractCoordinates(ForwardGeocodeDTO dto) {
        return new Coordinates(Double.parseDouble(dto.latitude()), Double.parseDouble(dto.longitude()));
    }
}
