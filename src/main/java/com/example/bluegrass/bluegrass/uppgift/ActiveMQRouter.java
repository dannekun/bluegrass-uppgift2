package com.example.bluegrass.bluegrass.uppgift;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class ActiveMQRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

/*
        from("timer:active-mq-timer?period=5000")
                .transform().constant("My message for Active MQ")
                .log("${body}").to("activemq:my-activemq-queue");

 */
    }
}
