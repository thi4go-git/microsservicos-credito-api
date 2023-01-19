package net.ddns.cloudtecnologia.cartoes.service;

import net.ddns.cloudtecnologia.cartoes.model.entity.Cartao;
import net.ddns.cloudtecnologia.cartoes.rest.dto.CartaoDTO;

import java.util.List;
import java.util.Optional;

public interface CartaoService {
    Cartao save(CartaoDTO dto);

    List<Cartao> getCartoesRendaMenorIgual(Long renda);

    Optional<Cartao> findById(Long id);
}
