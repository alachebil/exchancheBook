package com.example.BookExchange;

import com.example.BookExchange.entity.Role;
import com.example.BookExchange.entity.UserP;
import com.example.BookExchange.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class BookExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookExchangeApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run (UserService userService){
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//			userService.saveUser(new UserP(null,"ala chebil","chebilala","123456",new ArrayList<>()));
//			userService.saveUser(new UserP(null,"samer chebil","chebilSamer","123456",new ArrayList<>()));
//			userService.saveUser(new UserP(null,"mootaz nabli","nabliTazz","123456",new ArrayList<>()));
//
//			userService.addRoleToUser("chebilala","ROLE_USER");
//			userService.addRoleToUser("chebilala","ROLE_ADMIN");
//			userService.addRoleToUser("chebilSamer","ROLE_MANAGER");
//			userService.addRoleToUser("nabliTazz","ROLE_MANAGER");
//
//
//
//		};
//	}

}
