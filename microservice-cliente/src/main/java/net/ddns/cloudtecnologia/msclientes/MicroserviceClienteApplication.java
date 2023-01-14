package net.ddns.cloudtecnologia.msclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceClienteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceClienteApplication.class, args);
    }

}
