package nl.testautomation.demoapplication.backend;

import lombok.extern.slf4j.Slf4j;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.model.LoanReason;
import nl.testautomation.demoapplication.backend.repository.LoanReasonsRepository;
import nl.testautomation.demoapplication.backend.repository.LoanRequestRepository;
import nl.testautomation.demoapplication.backend.repository.LoanTypeRepository;
import nl.testautomation.demoapplication.backend.service.DemoGeneratorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
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
