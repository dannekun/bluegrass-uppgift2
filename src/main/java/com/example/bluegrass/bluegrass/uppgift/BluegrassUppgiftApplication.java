package com.example.bluegrass.bluegrass.uppgift;

import com.cmeza.sdgenerator.annotation.SDGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;



@SDGenerator(
		entityPackage = "generated",
		repositoryPackage = "com.example.bluegrass.bluegrass.uppgift.repositories",
		managerPackage = "com.example.bluegrass.bluegrass.uppgift.managers",
		repositoryPostfix = "Repository",
		managerPostfix = "Manager",
		onlyAnnotations = false,
		debug = false,
		overwrite = false,
		additionalExtends = {
				QuerydslPredicateExecutor.class
		},
		lombokAnnotations = false,
		withComments = true
)
@SpringBootApplication
public class BluegrassUppgiftApplication {
	public static void main(String[] args) {
		SpringApplication.run(BluegrassUppgiftApplication.class, args);
	}
}
