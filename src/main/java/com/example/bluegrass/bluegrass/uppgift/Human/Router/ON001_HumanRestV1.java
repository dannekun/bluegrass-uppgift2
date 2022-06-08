package com.example.bluegrass.bluegrass.uppgift.Human.Router;

import com.example.bluegrass.bluegrass.uppgift.Human.Bean.OFF001_DatabaseBeanV1;
import com.example.bluegrass.bluegrass.uppgift.repositories.HumanRepository;
import generated.Human;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.DefaultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.example.bluegrass.bluegrass.uppgift.Human.Human_Endpoints_V1.*;

@Component
public class ON001_HumanRestV1 extends RouteBuilder {

    @Autowired
    HumanRepository humanRepository;

    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(Human.class.getPackage().getName());

    @Autowired
    private OFF001_DatabaseBeanV1 OFF001DatabaseBeanV1;


    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet")
                .host("localhost")
                .contextPath("/")
                .port(8080)
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Danne API")
                .apiProperty("api.version", "1.0")
                .enableCORS(true);

        rest().consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE)

                .get("/human").outType(Human.class).to(REST_DIRECT_GET_HUMAN_V1_ENDPOINT)
                .description("Get data for all Humans")
                .responseMessage("200", "On good request")
                .responseMessage("404", "For invalid requests")

                .post("/human").type(Human.class).to(REST_DIRECT_SAVE_HUMAN_V1_ENDPOINT)
                .description("Save a human")
                .get("/human/{id}")
                .outType(Human.class)
                .to(REST_DIRECT_GET_SPECIFIC_HUMAN_V1_ENDPOINT);
/*
        from(REST_DIRECT_SAVE_HUMAN_V1_ENDPOINT)
                .process(this::findHuman)
                .marshal(jaxbDataFormat)
                .to(REST_ACTIVEMQ_SEND_V1_ENDPOINT);

 */

        from(REST_DIRECT_SAVE_HUMAN_V1_ENDPOINT)
                .choice()
                .when(method(OFF001DatabaseBeanV1)).log("DEN HITTA")
                .end()
                .process(this::findHuman)
                .marshal(jaxbDataFormat)
                .to(REST_ACTIVEMQ_SEND_V1_ENDPOINT);

        from(REST_DIRECT_GET_HUMAN_V1_ENDPOINT)
                .process(this::getHuman)
                .log(LOG_BODY);

        from(REST_DIRECT_GET_SPECIFIC_HUMAN_V1_ENDPOINT)
                .process(this::getHumanSpecific)
                .log(LOG_BODY);

    }

    private void getHumanSpecific(Exchange exchange) {
        String id = exchange.getIn().getHeader("ID", String.class);
        Optional<Human> human = humanRepository.findById(Long.valueOf(id));
        exchange.getIn().setBody(human.get());
    }


    private void findHuman(Exchange exchange){
        Human human = exchange.getIn().getBody(Human.class);
        Human dbHumanWithoutId = humanRepository.findByFirstNameAndLastNameAndHairColorAndBirthItem(human.getFirstName(), human.getLastName(), human.getHairColor(), human.getBirthItem());

        if (human.getHjid() == null){
            if (dbHumanWithoutId != null){
                human = dbHumanWithoutId;
                exchange.getIn().setBody(human);
            }else {
                exchange.getIn().setBody(humanRepository.save(human));
            }
        }else if (dbHumanWithoutId.getHjid() != null){
            exchange.getIn().setBody(dbHumanWithoutId);
        }else if (dbHumanWithoutId.getHjid() == null) {
            Optional<Human> humanWithSameId = humanRepository.findById(human.getHjid());
            if (humanWithSameId.isPresent()){
                if (human.getFirstName().equals(humanWithSameId.get().getFirstName()) &&
                human.getLastName().equals(humanWithSameId.get().getLastName()) &&
                human.getHairColor().equals(humanWithSameId.get().getHairColor()) &&
                human.getBirthItem().equals(humanWithSameId.get().getBirthItem())){
                    exchange.getIn().setBody(humanWithSameId);
                }else {
                    human.setHjid(null);
                    exchange.getIn().setBody(human);
                }
            }
        }
    }



    private void getHuman(Exchange exchange){
        List<Human> allHuman = humanRepository.findAll();
        Message message = new DefaultMessage(exchange.getContext());
        message.setBody(allHuman);
        exchange.setMessage(message);
    }
}



