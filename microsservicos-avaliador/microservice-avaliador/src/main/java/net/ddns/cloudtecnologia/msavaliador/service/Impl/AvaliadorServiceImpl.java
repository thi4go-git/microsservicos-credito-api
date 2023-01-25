package net.ddns.cloudtecnologia.msavaliador.service.Impl;

import feign.FeignException;
import net.ddns.cloudtecnologia.msavaliador.client.CartoesResourceClient;
import net.ddns.cloudtecnologia.msavaliador.client.ClienteResourceClient;
import net.ddns.cloudtecnologia.msavaliador.exception.DadosClienteNotFoundException;
import net.ddns.cloudtecnologia.msavaliador.exception.ErroComunicacaoMicroservicesException;
import net.ddns.cloudtecnologia.msavaliador.exception.ErroSolicitacaoCartaoException;
import net.ddns.cloudtecnologia.msavaliador.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import net.ddns.cloudtecnologia.msavaliador.model.entity.*;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.*;
import net.ddns.cloudtecnologia.msavaliador.service.AvaliadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliadorServiceImpl implements AvaliadorService {


    @Autowired
    private ClienteResourceClient clientesClient;

    @Autowired
    private CartoesResourceClient cartoesClient;


    @Autowired
    private SolicitacaoEmissaoCartaoPublisher publisher;


    @Override
    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosClienteDTO> dadosClienteResponse = clientesClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoClienteDTO>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }

    @Override
    public RetornoAvaliacaoClienteDTO realizarAvaliacao(String cpf, Long renda)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosClienteDTO> dadosClienteResponse = clientesClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoDTO>> cartoesResponse = cartoesClient.getCartoesRendaAteh(renda);

            List<CartaoDTO> cartoes = cartoesResponse.getBody();
            var listaCartoesAprovados = cartoes.stream().map(cartao -> {

                DadosClienteDTO dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovadoDTO aprovado = new CartaoAprovadoDTO();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoClienteDTO(listaCartoesAprovados);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }

    @Override
    public ProtocoloSolicitacaoCartaoDTO
    solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartaoDTO dados) {
        try {
            publisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartaoDTO(protocolo);
        } catch (Exception e) {
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }


}
