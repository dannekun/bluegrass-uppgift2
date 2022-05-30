package com.example.bluegrass.bluegrass.uppgift;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQReceiverRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

/*
        from("activemq:my-activemq-queue")
                .to("log:received-message-activemq");

 */
    }
}
