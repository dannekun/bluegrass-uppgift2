package com.example.bluegrass.bluegrass.uppgift.Weather.Router;

import com.example.bluegrass.bluegrass.uppgift.Weather.Processor.DatabaseProcessor;
import com.example.bluegrass.bluegrass.uppgift.Weather.Processor.TextFileProcessor;
import generated.WeatherData;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OFF001_WeatherDataV1_Database extends RouteBuilder {
    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(WeatherData.class.getPackageName());

    @Autowired
    DatabaseProcessor databaseProcessor;

    @Override
    public void configure() throws Exception {

        from("activemq:topic:WeatherDataV1")
                .log("Gör Weatherdata till Flatfile")
                .unmarshal(jaxbDataFormat)
                .process(databaseProcessor)
                .to("log:offramp-weatherdata?showAll=true");

    }
}