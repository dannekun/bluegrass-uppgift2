package com.example.bluegrass.bluegrass.uppgift;

import org.springframework.data.repository.CrudRepository;

public interface WeatherDataRepository extends CrudRepository<WeatherData, String> {
}
