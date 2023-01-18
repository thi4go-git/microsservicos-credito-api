package net.ddns.cloudtecnologia.msavaliador.service.Impl;

import feign.FeignException;
import net.ddns.cloudtecnologia.msavaliador.client.CartoesResourceClient;
import net.ddns.cloudtecnologia.msavaliador.client.ClienteResourceClient;
import net.ddns.cloudtecnologia.msavaliador.exception.DadosClienteNotFoundException;
import net.ddns.cloudtecnologia.msavaliador.exception.ErroComunicacaoMicroservicesException;
import net.ddns.cloudtecnologia.msavaliador.model.entity.*;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.CartaoClienteDTO;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.DadosClienteDTO;
import net.ddns.cloudtecnologia.msavaliador.service.AvaliadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliadorServiceImpl implements AvaliadorService {


    @Autowired
    private ClienteResourceClient clientClient;

    @Autowired
    private CartoesResourceClient cartoesClient;


    @Override
    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosClienteDTO> dadosClienteResponse = clientClient.dadosCliente(cpf);
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
    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) {
        return null;
    }

    @Override
    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
        return null;
    }
}
