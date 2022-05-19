package com.example.bluegrass.bluegrass.uppgift;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherRouter extends RouteBuilder {

    JacksonDataFormat jsonDataFormat = new JacksonDataFormat(WeatherData.Reading.Parameter.class);

    WeatherData weatherData = new WeatherData();

    @Autowired
    MyProcessor myProcessor;

    @Autowired
    WeatherDataRepositry weatherDataRepositry;

    @Override
    public void configure() throws Exception {
/*
      from("timer:rest-api?period=5000").setHeader(Exchange.HTTP_METHOD, simple("GET"))
              .to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/1/station/97400/period/latest-hour/data.json")
              .process(new MyProcessor()).to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/3/station/97400/period/latest-hour/data.json")
              .process(new MyProcessor()).to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/4/station/97400/period/latest-hour/data.json")
              .process(new MyProcessor());

 */


        from("timer:rest-api?period=5000").setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/3/station/97400/period/latest-hour/data.json")
                .process(myProcessor).marshal(jsonDataFormat)
                .to("log:rest-apis");
        from("timer:rest-api?period=5000").setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/1/station/97400/period/latest-hour/data.json")
                .process(myProcessor).marshal(jsonDataFormat)
                .to("log:rest-apis");
        from("timer:rest-api?period=5000").setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/4/station/97400/period/latest-hour/data.json")
                .process(myProcessor).marshal(jsonDataFormat)
                .to("log:rest-apis");

        //LÄGG TILL METOD HÄR FÖR RESTEN AV INFON
        //LÄGG TILL INFO HÄR FÖR ATT GÖRA TILL XML
        //LÄGG TILL INFO HÄR FÖR ATT GÖRA TILL TXT


      /*
        from("timer:rest-api?period=5000").setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/3/station/97400/period/latest-hour/data.json")
                .process(new MyProcessor());

        from("timer:rest-api?period=5000").setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/4/station/97400/period/latest-hour/data.json")
                .process(new MyProcessor());

       */

    }
}
