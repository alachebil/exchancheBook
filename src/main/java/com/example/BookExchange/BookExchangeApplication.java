package com.example.BookExchange;

import com.example.BookExchange.service.UserService;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
//
//			booksService.addBook(new Books("name","categorie","image", 55L));


//			userService.saveUser(new UserP(null,"ala chebil","chebilala","123456",new ArrayList<>()));
//			userService.saveUser(new UserP("samer chebil","chebilsamer","0000",96212001L,new ArrayList<>()));
			//userService.saveUser(new UserP("mootaz gay", "nabli", "123456", 303030L, new ArrayList<>()));
//
//			userService.addRoleToUser("nabli","ROLE_USER");
//			userService.addRoleToUser("chebilala","ROLE_ADMIN");
//			userService.addRoleToUser("chebilsamer","ROLE_MANAGER");
//			userService.addRoleToUser("nabliTazz","ROLE_MANAGER");
//
//
//
		};
	}

}
