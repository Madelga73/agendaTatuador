package com.sesion_vm.sesion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SesionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SesionApplication.class, args);
	}

}
