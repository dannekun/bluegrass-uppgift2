package com.example.bluegrass.bluegrass.uppgift.Human.Router;

import com.example.bluegrass.bluegrass.uppgift.Human.Processor.OFF002_HumanCsvV1Processor;
import generated.Human;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.bluegrass.bluegrass.uppgift.Human.Human_Endpoints_V1.*;

@Component
public class OFF002_HumanV1_CSV extends RouteBuilder {

    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(Human.class.getPackageName());
    @Autowired
    OFF002_HumanCsvV1Processor OFF002HumanCSVProcessor;

    @Override
    public void configure() throws Exception {

                from(REST_ACTIVEMQ_HUMAN_TOPIC_V1_ENDPOINT)
                .unmarshal(jaxbDataFormat)
                .process(OFF002HumanCSVProcessor)
                .marshal().csv()
                .to(FILE_PATH_HUMAN_CSV)
                .to(REST_LOG_HUMAN_TOPIC_V1_ENDPOINT)
                                    .end();





    }
}
