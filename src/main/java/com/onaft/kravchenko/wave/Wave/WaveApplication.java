package com.onaft.kravchenko.wave.Wave;

import com.onaft.kravchenko.wave.Wave.model.Account;
import com.onaft.kravchenko.wave.Wave.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Random;

@SpringBootApplication
public class WaveApplication{
	@Autowired
	AccountService accountService; //= context.getBean(AccountService.class);;

	public static void main(String[] args) {
		SpringApplication.run(WaveApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		Random r = new Random();

		// Account 1
		int cus_1_id = r.nextInt();
		Account cus_1 = new Account(cus_1_id,"account1","12345");

		accountService.insert(cus_1);
	}

	/*@Bean
	public WebMvcConfigurer corsConfig(){
		return new WebMvcConfigurerAdapter(){
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*");
			}
		};
	}*/
}
