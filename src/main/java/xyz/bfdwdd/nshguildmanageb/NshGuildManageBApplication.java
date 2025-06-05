package xyz.bfdwdd.nshguildmanageb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("xyz.bfdwdd.nshguildmanageb")
@SpringBootApplication
public class NshGuildManageBApplication {
	public static void main(String[] args) {
		SpringApplication.run(NshGuildManageBApplication.class, args);
	}
}