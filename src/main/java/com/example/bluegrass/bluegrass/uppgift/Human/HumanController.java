package com.example.bluegrass.bluegrass.uppgift.Human;


import com.example.bluegrass.bluegrass.uppgift.Human.Processor.HumanJsonProcessReceiver;
import com.example.bluegrass.bluegrass.uppgift.repositories.HumanRepository;
import generated.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



public class HumanController {


    @Autowired
    HumanRepository humanRepository;

    @Autowired
    HumanJsonProcessReceiver humanJsonProcessReceiver;

    @Autowired
    HumanGlobal global;

    @GetMapping("/human")
    List<Human> all(){
        return humanRepository.findAll();
    }

/*
    @PostMapping("/human/{firstName}/{lastName}/{birth}/{hairColor}")
    Human newHuman(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String birth, @PathVariable String hairColor){
        Human human = new Human();
        human.setFirstName(firstName);
        human.setLastName(lastName);
        human.setHairColor(hairColor);



        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(birth);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar xmlGregCal;
        try {
          xmlGregCal   =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        human.setBirth(xmlGregCal);

        global.setHuman(human);

        return humanRepository.save(human);
    }

 */

@PostMapping("/human")
Human newHuman(@RequestBody Human human){

    return humanRepository.save(human);
}





}
