package nl.testautomation.demoapplication.backend;

import lombok.extern.slf4j.Slf4j;
import nl.testautomation.demoapplication.backend.model.Loan;
import nl.testautomation.demoapplication.backend.repository.LoanRepository;
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
	CommandLineRunner init(LoanRepository loanRepository) {
		return args -> {
			loanRepository.save(new Loan()
					.setTitle("Mini-loan")
					.setMinAmount(300));
			loanRepository.save(new Loan()
					.setTitle("Car-loan")
					.setMinAmount(2000));
			loanRepository.save(new Loan()
					.setTitle("Mortgage")
					.setMinAmount(50000));

			loanRepository.findAll().forEach(l -> log.info("Loan in DB: {}", l));
		};
	}

}
