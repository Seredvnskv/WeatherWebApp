package com.example.weatherwebapp.client.geocode.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressDTO(
        String attraction,
        String road,
        String suburb,
        String city,
        String state,
        String postcode,
        String country,
        @JsonProperty("country_code") String countryCode
) {}
