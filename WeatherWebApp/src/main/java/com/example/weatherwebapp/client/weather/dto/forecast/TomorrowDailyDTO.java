package com.example.weatherwebapp.client.weather.dto.forecast;

import java.time.LocalDateTime;

public record TomorrowDailyDTO(
        LocalDateTime time,
        TomorrowForecastValuesDTO values
) { }
