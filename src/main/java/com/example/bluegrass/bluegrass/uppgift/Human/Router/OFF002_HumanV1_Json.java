package com.example.bluegrass.bluegrass.uppgift.Human.Router;

import com.example.bluegrass.bluegrass.uppgift.Human.Processor.OFF002_HumanJsonV1Process;
import generated.Human;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.example.bluegrass.bluegrass.uppgift.Human.Human_Endpoints_V1.*;

@Component
public class OFF002_HumanV1_Json extends RouteBuilder {

    @Autowired
    OFF002_HumanJsonV1Process OFF002HumanJsonV1Process;

    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(Human.class.getPackageName());
    @Override
    public void configure() throws Exception {

        from(REST_ACTIVEMQ_HUMAN_TOPIC_V1_ENDPOINT)
                .unmarshal(jaxbDataFormat)
                .process(OFF002HumanJsonV1Process)
                .marshal().json()
                .to(FILE_PATH_HUMAN_JSON)
                .to(REST_LOG_HUMAN_TOPIC_V1_ENDPOINT)
                .end();


    }
}
