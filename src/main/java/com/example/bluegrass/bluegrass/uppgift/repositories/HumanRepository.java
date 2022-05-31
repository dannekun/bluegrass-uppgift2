package com.example.bluegrass.bluegrass.uppgift.repositories;

import generated.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
* Generated by Spring Data Generator on 31/05/2022
*/
@Repository
public interface HumanRepository extends JpaRepository<Human, Long>, JpaSpecificationExecutor<Human>, QuerydslPredicateExecutor<Human> {

    Human findTopByOrderByHjidDesc();
}
