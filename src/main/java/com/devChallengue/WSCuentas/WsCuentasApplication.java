package com.devChallengue.WSCuentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableFeignClients(basePackages = "com.devChallengue.WSCuentas.cliente")
public class WsCuentasApplication {

	public static void main(String[] args) {

		SpringApplication.run(WsCuentasApplication.class, args);
	}

}
