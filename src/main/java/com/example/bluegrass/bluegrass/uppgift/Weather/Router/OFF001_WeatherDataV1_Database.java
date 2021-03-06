package com.example.bluegrass.bluegrass.uppgift.Weather.Router;

import com.example.bluegrass.bluegrass.uppgift.Weather.Processor.OFF001_DatabaseProcessor;
import generated.WeatherData;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OFF001_WeatherDataV1_Database extends RouteBuilder {
    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(WeatherData.class.getPackageName());

    @Autowired
    OFF001_DatabaseProcessor OF001DatabaseProcessor;

    @Override
    public void configure() throws Exception {

        from("activemq:topic:WeatherDataTopicV1")
                .log("Laddar upp WeatherDate till databasen")
                .unmarshal(jaxbDataFormat)
                .process(OF001DatabaseProcessor)
                .to("log:offramp-weatherdata?showAll=true");

    }
}