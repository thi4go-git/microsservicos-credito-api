package net.ddns.cloudtecnologia.msavaliador.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String emissaoCartoesfila;

    public Queue queeuEmissaoCartoes() {
        return new Queue(emissaoCartoesfila, true);
    }

}
