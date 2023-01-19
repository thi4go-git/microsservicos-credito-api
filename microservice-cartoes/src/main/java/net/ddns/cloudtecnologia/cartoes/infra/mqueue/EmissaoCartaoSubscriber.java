package net.ddns.cloudtecnologia.cartoes.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.ddns.cloudtecnologia.cartoes.model.entity.Cartao;
import net.ddns.cloudtecnologia.cartoes.model.entity.CartaoCliente;
import net.ddns.cloudtecnologia.cartoes.rest.dto.DadosSolicitacaoEmissaoCartaoDTO;
import net.ddns.cloudtecnologia.cartoes.service.impl.CartaoClienteServiceImpl;
import net.ddns.cloudtecnologia.cartoes.service.impl.CartaoServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartaoSubscriber {

    @Autowired
    private CartaoServiceImpl cartaoService;

    @Autowired
    private CartaoClienteServiceImpl cartaoClienteService;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        var mapper = new ObjectMapper();
        try {
            DadosSolicitacaoEmissaoCartaoDTO dados =
                    mapper.readValue(payload, DadosSolicitacaoEmissaoCartaoDTO.class);
            Cartao cartao = cartaoService.findById(dados.getIdCartao()).orElseThrow();
            CartaoCliente cartaoCliente = new CartaoCliente();
            cartaoCliente.setCartao(cartao);
            cartaoCliente.setCpf(dados.getCpf());
            cartaoCliente.setLimite(dados.getLimiteLiberado());

            cartaoClienteService.save(cartaoCliente);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
