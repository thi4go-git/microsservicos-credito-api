package net.ddns.cloudtecnologia.msavaliador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceAvaliadorApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAvaliadorApplication.class, args);
	}
}
