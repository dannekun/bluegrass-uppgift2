package com.example.bluegrass.bluegrass.uppgift;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DatabaseProcessor implements Processor {

    @Autowired
    WeatherService weatherService;

    @Autowired
    WeatherDataRepository weatherDataRepository;

    @Transactional
    @Override
    public void process(Exchange exchange) throws Exception {
        weatherDataRepository.save(weatherService.globalWeather);
        exchange.getIn().setBody(weatherService.stringWeather(weatherService.globalWeather));
    }
}
