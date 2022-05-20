package com.example.bluegrass.bluegrass.uppgift;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Component
public class TextFileProcessor implements Processor {

    @Autowired
    WeatherService weatherService;

    int x = 0;

    @Transactional
    @Override
    public void process(Exchange exchange) throws Exception {
        String filename = "./"+weatherService.globalWeather.getReading().get(0).getStationName()+".txt";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(filename, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String stringToPrint = weatherService.globalWeather.getReading().get(0).getTimestamp()+
                "," + weatherService.globalWeather.getReading().get(0).getStationName()+
                ","+ weatherService.globalWeather.getReading().get(0).getParameter().get(0).getValue()+
                "," + weatherService.globalWeather.getReading().get(0).getParameter().get(1).getValue() +
                ","+ weatherService.globalWeather.getReading().get(0).getParameter().get(2).getValue();

        writer.println(stringToPrint);
        writer.close();
        exchange.getIn().setBody(stringToPrint);
        }

}
