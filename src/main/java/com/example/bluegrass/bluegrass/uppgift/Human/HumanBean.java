package com.example.bluegrass.bluegrass.uppgift.Human;

import generated.Human;
import org.springframework.stereotype.Component;

@Component
public class HumanBean {


    public Human response(Human human){
        human.setFirstName(human.getFirstName().toUpperCase());
        System.out.println("FUNKAR");
        return human;
    }

}
