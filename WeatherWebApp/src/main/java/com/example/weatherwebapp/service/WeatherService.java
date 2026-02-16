package com.example.weatherwebapp.service;

import com.example.weatherwebapp.client.weather.WeatherClient;
import com.example.weatherwebapp.mapper.WeatherMapper;
import com.example.weatherwebapp.model.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final WeatherClient weatherClient;

    @Autowired
    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @Cacheable(value = "weatherCache", key = "#latitude + ',' + #longitude")
    public WeatherDTO getRealTimeWeather(double latitude, double longitude) {
        return WeatherMapper.toWeatherDTO(weatherClient.getRealTimeWeather(latitude, longitude));
    }

    @Cacheable(value = "weatherCache", key = "#location")
    public WeatherDTO getRealTimeWeather(String location) {
        return WeatherMapper.toWeatherDTO(weatherClient.getRealTimeWeather(location));
    }
 }
