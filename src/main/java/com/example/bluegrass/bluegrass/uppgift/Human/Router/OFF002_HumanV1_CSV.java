package com.example.bluegrass.bluegrass.uppgift.Human.Router;

import com.example.bluegrass.bluegrass.uppgift.Human.Processor.HumanCSVProcessor;
import generated.Human;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OFF002_HumanV1_CSV extends RouteBuilder {

    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(Human.class.getPackageName());
    @Autowired
    HumanCSVProcessor humanCSVProcessor;

    @Override
    public void configure() throws Exception {

        from("activemq:topic:HumanTopicV1")
                .unmarshal(jaxbDataFormat)
                .process(humanCSVProcessor)
                .marshal().csv()
                .to("file:files/Human/CSV?fileName=${header.CamelFileName}.csv")
                .to("log:receivedJackson");


    }
}
