package br.com.fiap.deluxegames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MoneyminderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyminderApplication.class, args);
	}

}
