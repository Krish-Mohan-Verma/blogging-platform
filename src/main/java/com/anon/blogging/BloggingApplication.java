package com.anon.blogging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EntityScan("com.anon.blogging.Entity")
public class BloggingApplication {

	public static void main(String[] args) {
		System.out.println("DEBUG URL: " + System.getenv("MYSQL_ADDON_HOST") + ":" + System.getenv("MYSQL_ADDON_PORT"));
		SpringApplication.run(BloggingApplication.class, args);
	}

}
