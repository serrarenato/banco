package br.com.bancos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("br.com.bancos")
public class BancosApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancosApplication.class, args);
	}
}
