package com.example.weatherwebapp.repository;

import com.example.weatherwebapp.model.WeatherQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WeatherQueryRepository extends JpaRepository<WeatherQuery, UUID> {

}
