package com.example.bluegrass.bluegrass.uppgift.Human.Router;

import com.example.bluegrass.bluegrass.uppgift.Human.Processor.HumanJsonProcessReceiver;
import generated.Human;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OFF002_HumanV1_Json extends RouteBuilder {

    @Autowired
    HumanJsonProcessReceiver humanJsonProcessReceiver;

    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(Human.class.getPackageName());
    @Override
    public void configure() throws Exception {

        from("activemq:topic:HumanTopicV1")
                .unmarshal(jaxbDataFormat)
                .process(humanJsonProcessReceiver)
                .marshal().json()
                .to("file:files/Human/JSON?fileName=${header.CamelFileName}.json")
                .to("log:receivedJackson");
    }
}
