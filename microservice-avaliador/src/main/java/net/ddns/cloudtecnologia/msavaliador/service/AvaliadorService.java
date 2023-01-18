package net.ddns.cloudtecnologia.msavaliador.service;

import net.ddns.cloudtecnologia.msavaliador.exception.DadosClienteNotFoundException;
import net.ddns.cloudtecnologia.msavaliador.exception.ErroComunicacaoMicroservicesException;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.DadosSolicitacaoEmissaoCartaoDTO;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.ProtocoloSolicitacaoCartaoDTO;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.RetornoAvaliacaoClienteDTO;
import net.ddns.cloudtecnologia.msavaliador.model.entity.SituacaoCliente;

public interface AvaliadorService {
    SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException;

    RetornoAvaliacaoClienteDTO realizarAvaliacao(String cpf, Long renda)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException;

    ProtocoloSolicitacaoCartaoDTO solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartaoDTO dados);
}
