package com.example.weatherwebapp.service;

import com.example.weatherwebapp.client.weather.WeatherClient;
import com.example.weatherwebapp.client.weather.dto.TomorrowRealTimeWeatherDTO;
import com.example.weatherwebapp.model.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final WeatherClient weatherClient;

    @Autowired
    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public TomorrowRealTimeWeatherDTO getRealTimeWeather(double latitude, double longitude) {
        return weatherClient.getRealTimeWeather(latitude, longitude);
    }

    public TomorrowRealTimeWeatherDTO getRealTimeWeather(String location) {
        return weatherClient.getRealTimeWeather(location);
    }
 }
