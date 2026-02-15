package com.example.weatherwebapp.service;

import com.example.weatherwebapp.client.geocode.GeocodingClient;
import com.example.weatherwebapp.exception.LocationNotFoundException;
import com.example.weatherwebapp.client.geocode.dto.ForwardGeocodeDTO;
import com.example.weatherwebapp.client.geocode.dto.ReverseGeocodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeocodingService {
    private final GeocodingClient geocodingClient;

    @Autowired
    public GeocodingService (GeocodingClient geocodingClient) {
        this.geocodingClient = geocodingClient;
    }

    public ForwardGeocodeDTO forwardGeocodingRequest(String location) throws LocationNotFoundException {
        return geocodingClient.forwardGeocodingRequest(location);
    }

    public ReverseGeocodeDTO reverseGeocodingRequest(double latitude, double longitude) throws LocationNotFoundException {
        return geocodingClient.reverseGeocodeDTO(latitude, longitude);
    }
}
