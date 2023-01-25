package net.ddns.cloudtecnologia.msavaliador.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.DadosSolicitacaoEmissaoCartaoDTO;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolicitacaoEmissaoCartaoPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue filaEmissaoCartoes;


    public void solicitarCartao(DadosSolicitacaoEmissaoCartaoDTO dados)
            throws JsonProcessingException {
        var json = converteIntoJson(dados);
        rabbitTemplate.convertAndSend(filaEmissaoCartoes.getName(), json);
    }


    public String converteIntoJson(DadosSolicitacaoEmissaoCartaoDTO dados)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dados);
        return json;
    }

}
