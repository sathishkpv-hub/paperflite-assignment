package com.assesment.spring.data.mongodb;

import com.assesment.spring.data.mongodb.service.CustomerMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDataMongodbApplication implements CommandLineRunner {

	@Autowired
	private CustomerMigrationService migrationService;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataMongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int deleted = migrationService.deleteCustomersWithInvalidAccounts();
		System.out.println("Deleted customers: " + deleted);
	}
}
