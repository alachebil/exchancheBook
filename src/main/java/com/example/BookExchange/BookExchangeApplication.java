package com.example.BookExchange;

import com.example.BookExchange.dto.UserDtoCreation;
import com.example.BookExchange.entity.Books;
import com.example.BookExchange.entity.Role;
import com.example.BookExchange.entity.User;
import com.example.BookExchange.service.UserService;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
/*import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;*/

@SpringBootApplication
//@OpenAPIDefinition(
//		info = @Info(title="books exchange",
//		version="1.0.0",license =@License(name="license",
//				url="runcodenow")
//		)
//)
@EnableScheduling
public class BookExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookExchangeApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	JavaMailSender javaMailSender()
	{
		return new JavaMailSenderImpl();
	}


//	@Bean
//	public Docket docket(){
//		return new Docket(DocumentationType.SWAGGER_2).groupName("books-api").apiInfo(apiInfo())
//				.select().apis(RequestHandlerSelectors.basePackage("com.example.BookExchange.api"))
//				.paths(PathSelectors.any())
//				.build();
//	}
//
//	private ApiInfo apiInfo(){
//		return new ApiInfoBuilder().title("User Api").description("spring rest api reference")
//				.licenseUrl("ala.chebil@esprit.tn")
//				.version("1.0")
//				.build();
//	}


	@Bean
	CommandLineRunner run (UserService userService){
		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			//booksService.addBook(new Books("name","categorie","image", 55L));


//  !!!!!!!!!!!!!!!   loula hya s7i7a bel dto  !!!!!!!!!!!
			userService.saveUser(new UserDtoCreation("ala chebil","chebilraja","ala.chebil@esprit.com",96212001L,"0000"));
//			userService.saveUser(new User("samer chebil","chebilsamer","0000",96212001L,"samer.chebil@esprit.com",new ArrayList<>()));
//			userService.saveUser(new User("mootaz nabli", "nabli", "123456", 303030L, "motaz.chebil@esprit.com",new ArrayList<>()));

//			userService.addRoleToUser("nabli","ROLE_USER");
			userService.addRoleToUser("chebilraja","ROLE_ADMIN");
//			userService.addRoleToUser("chebilsamer","ROLE_MANAGER");
//			userService.addRoleToUser("nabliTazz","ROLE_MANAGER");




		};
	}

}
