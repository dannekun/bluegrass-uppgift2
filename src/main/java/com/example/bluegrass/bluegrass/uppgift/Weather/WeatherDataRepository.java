package com.example.bluegrass.bluegrass.uppgift.Weather;

import com.example.bluegrass.bluegrass.uppgift.classes.WeatherData;
import org.springframework.data.repository.CrudRepository;

public interface WeatherDataRepository extends CrudRepository<WeatherData, String> {
}
