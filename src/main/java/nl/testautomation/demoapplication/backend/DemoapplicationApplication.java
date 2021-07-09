package nl.testautomation.demoapplication.backend;

import lombok.extern.slf4j.Slf4j;
import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.model.LoanReason;
import nl.testautomation.demoapplication.backend.repository.LoanReasonsRepository;
import nl.testautomation.demoapplication.backend.repository.LoanTypeRepository;
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
	CommandLineRunner init(LoanTypeRepository loanTypeRepository, LoanReasonsRepository loanReasonsRepository) {
		return args -> {
			loanTypeRepository.save(new LoanType()
					.setTitle("Mini-loan")
					.setMinAmount(300));
			loanTypeRepository.save(new LoanType()
					.setTitle("Personal loan")
					.setMinAmount(1500));
			loanTypeRepository.save(new LoanType()
					.setTitle("Revolving credit")
					.setMinAmount(2500));
			loanTypeRepository.save(new LoanType()
					.setTitle("Car-loan")
					.setMinAmount(2000));
			loanTypeRepository.save(new LoanType()
					.setTitle("Mortgage")
					.setMinAmount(50000));

			loanReasonsRepository.save(
					new LoanReason()
							.setTitle("Groceries")
							.setDescription("It is not a good idea to apply for a loan for just groceries.")
			);
			loanReasonsRepository.save(
					new LoanReason()
							.setTitle("Investments")
							.setDescription("Perhaps it is not such a good idea to apply for a loan for just investments.")
			);
			loanReasonsRepository.save(
					new LoanReason()
							.setTitle("Bills")
							.setDescription("A Revolving Credit or a Personal loan is probably the way to go.")
			);
			loanReasonsRepository.save(
					new LoanReason()
							.setTitle("Furniture")
							.setDescription("A mini-loan or a Personal loan is probably the best choice.")
			);
			loanReasonsRepository.save(
					new LoanReason()
							.setTitle("Car")
							.setDescription("A Car-loan is the best choice when buying a car.")
			);
			loanReasonsRepository.save(
					new LoanReason()
							.setTitle("House")
							.setDescription("You need a mortgage.")
			);
		};
	}

}
