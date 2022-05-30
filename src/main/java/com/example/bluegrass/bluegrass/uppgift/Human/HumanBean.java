package com.example.bluegrass.bluegrass.uppgift.Human;

import com.example.bluegrass.bluegrass.uppgift.classes.Human;
import org.springframework.stereotype.Component;

@Component
public class HumanBean {


    public Human response(Human human){
        human.setFirstName(human.getFirstName().toUpperCase());
        System.out.println("FUNKAR");
        return human;
    }

}
