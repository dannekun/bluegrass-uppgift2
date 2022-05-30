package com.example.bluegrass.bluegrass.uppgift.Human.Router;

import com.example.bluegrass.bluegrass.uppgift.CheckClassProcessor;
import com.example.bluegrass.bluegrass.uppgift.Human.Processor.GlobalXMLFileProcessor;
import com.example.bluegrass.bluegrass.uppgift.Human.Processor.HumanCSVProcessor;
import com.example.bluegrass.bluegrass.uppgift.Human.Processor.HumanJsonProcessReceiver;
import com.example.bluegrass.bluegrass.uppgift.Human.Processor.HumanTxtProcessor;
import com.example.bluegrass.bluegrass.uppgift.classes.Human;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class _3_HumanReceiveRouter extends RouteBuilder {

    @Autowired
    HumanJsonProcessReceiver humanJsonProcessReceiver;


    @Autowired
    GlobalXMLFileProcessor globalXMLFileProcessor;

    @Autowired
    CheckClassProcessor checkClassProcessor;

    @Autowired
    HumanTxtProcessor humanTxtProcessor;

    @Autowired
    HumanCSVProcessor humanCSVProcessor;

    @Override
    public void configure() throws Exception {
        from("activemq:fileRouter")
                .unmarshal().json(JsonLibrary.Jackson, Human.class)
                .log("${body}")
                .process(humanJsonProcessReceiver)
                .log("${body}")
                .process(globalXMLFileProcessor)
                .log("${body}")
                .process(humanTxtProcessor)
                .log("${body}")
                .process(humanCSVProcessor)
                .to("log:receivedJackson");
    }
}
