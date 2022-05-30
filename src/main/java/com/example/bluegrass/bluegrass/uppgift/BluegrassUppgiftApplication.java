package com.example.bluegrass.bluegrass.uppgift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

@SpringBootApplication
public class BluegrassUppgiftApplication {

	public static void main(String[] args) {
		SpringApplication.run(BluegrassUppgiftApplication.class, args);
	}

	/*
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean registration =
				new ServletRegistrationBean((Servlet) new CamelHttpTransportServlet(), "/api/*");
		registration.setName("CamelServlet");
		return registration;
	}

	 */

}
