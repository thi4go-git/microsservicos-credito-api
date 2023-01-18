package net.ddns.cloudtecnologia.msavaliador.service;

import net.ddns.cloudtecnologia.msavaliador.exception.DadosClienteNotFoundException;
import net.ddns.cloudtecnologia.msavaliador.exception.ErroComunicacaoMicroservicesException;
import net.ddns.cloudtecnologia.msavaliador.model.entity.DadosSolicitacaoEmissaoCartao;
import net.ddns.cloudtecnologia.msavaliador.model.entity.ProtocoloSolicitacaoCartao;
import net.ddns.cloudtecnologia.msavaliador.model.entity.RetornoAvaliacaoCliente;
import net.ddns.cloudtecnologia.msavaliador.model.entity.SituacaoCliente;

public interface AvaliadorService {
    SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException;

    RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda);

    ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados);
}
