package com.example.bluegrass.bluegrass.uppgift.Weather.Processor;

import com.example.bluegrass.bluegrass.uppgift.repositories.WeatherDataRepository;
import generated.WeatherData;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class OFF001_DatabaseProcessor implements Processor {
    @Autowired
    WeatherDataRepository weatherDataRepository;

    @Transactional
    @Override
    public void process(Exchange exchange) throws Exception {
        WeatherData weatherData = exchange.getIn().getBody(WeatherData.class);
        weatherDataRepository.save(weatherData);
        System.out.println("Sparat");
    }
}
