package com.example.weatherwebapp.client.geocode;

import com.example.weatherwebapp.exception.LocationNotFoundException;
import com.example.weatherwebapp.client.geocode.dto.ForwardGeocodeDTO;
import com.example.weatherwebapp.client.geocode.dto.ReverseGeocodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
@Slf4j
public class GeocodingClient {
    private final WebClient webClient;
    @Value("${geolocation.api.key}")
    private String GEOLOCATION_API_KEY;

    @Autowired
    public GeocodingClient (WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://us1.locationiq.com").build();
    }

    public ForwardGeocodeDTO forwardGeocodingRequest(String location) throws LocationNotFoundException {
        ForwardGeocodeDTO[] dto = callGetMethod("/v1/search", ForwardGeocodeDTO[].class, Map.of("q", location));

        if (dto == null || dto.length == 0) {
            log.error("No geocoding results found for location: {}", location);
            throw new LocationNotFoundException("Location not found: " + location);
        }

        return dto[0];
    }

    public ReverseGeocodeDTO reverseGeocodeDTO(double latitude, double longitude) throws LocationNotFoundException {
        return callGetMethod("/v1/reverse", ReverseGeocodeDTO.class, Map.of("lat", latitude, "lon", longitude));
    }

    private <T> T callGetMethod(String path, Class<T> responseType, Map<String, Object> queryParams) {
        return webClient.get()
                .uri(uriBuilder -> {
                    var builder = uriBuilder
                            .path(path)
                            .queryParam("key", GEOLOCATION_API_KEY)
                            .queryParam("format", "json");
                    queryParams.forEach(builder::queryParam);
                    return builder.build();
                })
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }
}
