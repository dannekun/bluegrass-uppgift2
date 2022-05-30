package com.example.bluegrass.bluegrass.uppgift.Human;

import com.example.bluegrass.bluegrass.uppgift.classes.Human;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HumanRepository extends JpaRepository<Human, Long> {

    Human findTopByOrderByHjidDesc();
}
