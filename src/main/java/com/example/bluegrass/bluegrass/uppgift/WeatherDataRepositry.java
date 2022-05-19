package com.example.bluegrass.bluegrass.uppgift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WeatherDataRepositry extends CrudRepository<WeatherData, String> {
}
