package com.example.bluegrass.bluegrass.uppgift.Human.Router;

import com.example.bluegrass.bluegrass.uppgift.Human.Processor.HumanProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class _1_HumanRouter extends RouteBuilder {

    @Autowired
    HumanProcessor humanProcessor;


    @Override
    public void configure() throws Exception {
/*
        from("timer:jsonConver?period=100000")
                .process(humanProcessor)
                .to("log:humanActive");

 */


    }
}
