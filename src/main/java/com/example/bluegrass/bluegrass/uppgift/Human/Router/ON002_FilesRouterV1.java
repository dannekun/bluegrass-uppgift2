package com.example.bluegrass.bluegrass.uppgift.Human.Router;

import com.example.bluegrass.bluegrass.uppgift.Human.Human_Endpoints_V1;
import com.example.bluegrass.bluegrass.uppgift.Human.Processor.OFF02_ClassProcessor;
import generated.Human;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.bluegrass.bluegrass.uppgift.Human.Human_Endpoints_V1.*;

@Component
public class ON002_FilesRouterV1 extends RouteBuilder {

    @Autowired
    OFF02_ClassProcessor off02_classProcessor;

    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(Human.class.getPackageName());

    @Override
    public void configure() throws Exception {


        from(REST_ACTIVEMQ_SEND_V1_ENDPOINT)
                .log(LOG_BODY)
                .unmarshal(jaxbDataFormat)
                .process(off02_classProcessor).marshal(jaxbDataFormat)
                .to(FILE_PATH_HUMAN_XML)
                .to(REST_ACTIVEMQ_HUMAN_TOPIC_V1_ENDPOINT);

    }
}
