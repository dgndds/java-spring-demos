package com.learning.noteapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NoteappApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteappApplication.class, args);
	}

}
