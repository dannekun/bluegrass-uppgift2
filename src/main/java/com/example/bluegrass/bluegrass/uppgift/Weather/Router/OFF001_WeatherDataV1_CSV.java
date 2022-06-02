package com.example.bluegrass.bluegrass.uppgift.Weather.Router;

import com.example.bluegrass.bluegrass.uppgift.Weather.Processor.OFF001_CSVProcessor;
import generated.WeatherData;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OFF001_WeatherDataV1_CSV extends RouteBuilder {

    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(WeatherData.class.getPackageName());

    @Autowired
    OFF001_CSVProcessor off001_csvProcessor;

    @Override
    public void configure() throws Exception {

        from("activemq:topic:WeatherDataTopicV1")
                .log("Gör Weatherdata till CSV")
                .unmarshal(jaxbDataFormat)
                .process(off001_csvProcessor)
                .marshal().csv()
                .to("log:offramp-weatherdata?showAll=true")
                .to("file:temp?fileName=test.csv");

    }
}