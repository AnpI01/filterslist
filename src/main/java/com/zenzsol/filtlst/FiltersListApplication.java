package com.zenzsol.filtlst;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class FiltersListApplication {
	
	private static final Logger log = Logger.getLogger(FiltersListApplication.class
			.getName());

	public static void main(String[] args) {
		log.info("application starts--");
		SpringApplication.run(FiltersListApplication.class, args);
	}


}
