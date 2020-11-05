package hu.sabikov.idomsoft.assignment.microservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Microservice2Application {

	public static void main(String[] args) {
		SpringApplication.run(Microservice2Application.class, args);
	}

}
