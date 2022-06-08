package com.example.bluegrass.bluegrass.uppgift.Weather.Router;

import com.example.bluegrass.bluegrass.uppgift.Human.Processor.OFF002_HumanCsvV1Processor;
import generated.WeatherData;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OFF001_WeatherDataV1_FlatFile extends RouteBuilder {

    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(WeatherData.class.getPackageName());

    @Autowired
    OFF002_HumanCsvV1Processor OFF002HumanCSVProcessor;

    @Override
    public void configure() throws Exception {

        from("activemq:topic:HumanTopicV1")
                .unmarshal(jaxbDataFormat)
                .process(OFF002HumanCSVProcessor)
                .marshal().csv()
                .to("file:files/Human/TXT?fileName=${header.CamelFileName}.txt")
                .to("log:receivedJackson");

    }
}