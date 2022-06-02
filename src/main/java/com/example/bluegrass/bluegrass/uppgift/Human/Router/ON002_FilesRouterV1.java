package com.example.bluegrass.bluegrass.uppgift.Human.Router;

import com.example.bluegrass.bluegrass.uppgift.Human.Processor.OFF02_ClassProcessor;
import generated.Human;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ON002_FilesRouterV1 extends RouteBuilder {

    @Autowired
    OFF02_ClassProcessor off02_classProcessor;

    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(Human.class.getPackageName());

    @Override
    public void configure() throws Exception {

        from("activemq:restRoute")
                .log("${body}")
                .unmarshal(jaxbDataFormat)
                .process(off02_classProcessor).marshal(jaxbDataFormat)
                .to("file:files/Human/XML?fileName=${header.CamelFileName}.xml")
                .to("activemq:topic:HumanTopicV1");


    }
}
