package org.bh.housing.mazaya;

import org.bh.housing.mazaya.repository.generic.HMBaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = HMBaseRepositoryImpl.class)
@EnableTransactionManagement
public class HousingMazayaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousingMazayaApplication.class, args);
	}
}
