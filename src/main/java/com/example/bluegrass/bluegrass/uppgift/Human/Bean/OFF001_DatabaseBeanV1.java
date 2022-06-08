package com.example.bluegrass.bluegrass.uppgift.Human.Bean;

import com.example.bluegrass.bluegrass.uppgift.repositories.HumanRepository;
import generated.Human;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OFF001_DatabaseBeanV1 {

    @Autowired
    HumanRepository humanRepository;
    public boolean doesItExistInTheDatabase(Exchange exchange){

        Logger logger = LoggerFactory.getLogger(OFF001_DatabaseBeanV1.class);

        Human human = exchange.getIn().getBody(Human.class);

        Human newHuman = humanRepository.findByFirstNameAndLastNameAndHairColorAndBirthItem(human.getFirstName(),human.getLastName(),human.getHairColor(),human.getBirthItem());
        System.out.println("human är inte null");
        if (newHuman != null){
            System.out.println("newHuman är inte null");
            logger.info("DEN FINNS");
            return true;
        }else {
            logger.info("DEN FINNS INTE");
            return false;
        }


    }
}
