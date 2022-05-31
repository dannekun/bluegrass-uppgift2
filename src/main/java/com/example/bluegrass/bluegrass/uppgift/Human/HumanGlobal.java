package com.example.bluegrass.bluegrass.uppgift.Human;

import generated.Human;
import org.springframework.stereotype.Service;

@Service
public class HumanGlobal {
    Human human;

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }
}
