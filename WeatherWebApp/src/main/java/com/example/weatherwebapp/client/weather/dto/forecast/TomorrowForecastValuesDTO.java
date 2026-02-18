package com.example.weatherwebapp.client.weather.dto.forecast;

import java.time.LocalDateTime;

public record TomorrowForecastValuesDTO(
        LocalDateTime sunriseTime,
        LocalDateTime sunsetTime,
        double temperatureAvg,
        double temperatureMax,
        double temperatureMin,
        double humidityAvg,
        double humidityMax,
        double humidityMin,
        double visibilityAvg,
        double windSpeedAvg,
        double rainIntensityAvg,
        double pressureSeaLevelAvg,
        double weatherCodeMax,
        double weatherCodeMin
) {

}
