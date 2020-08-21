package com.itsupport.todolist;

import com.itsupport.todolist.entities.Task;
import com.itsupport.todolist.entities.User;
import com.itsupport.todolist.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@AllArgsConstructor
@SpringBootApplication
public class TodolistApplication {

	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}


	public void run(String... args) throws Exception {
		User user = userRepository.findByEmail("vania.bondarev@gmail.com").orElseThrow(Exception::new);
		user.getTasks().add(Task.builder().description("456").build());
		user.getTasks().add(Task.builder().description("464564").build());
		userRepository.save(user);
	}
}
