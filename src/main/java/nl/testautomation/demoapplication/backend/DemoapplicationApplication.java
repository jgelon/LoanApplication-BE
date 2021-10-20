package nl.testautomation.demoapplication.backend;

import nl.testautomation.demoapplication.backend.service.DemoGeneratorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoapplicationApplication.class, args);
	}

	@Bean
	CommandLineRunner init(DemoGeneratorService demoGeneratorService) {
		return args -> {
			demoGeneratorService.generateDefaultLoanTypes();
			demoGeneratorService.generateDefaultLoanReasons();
		};
	}

}
