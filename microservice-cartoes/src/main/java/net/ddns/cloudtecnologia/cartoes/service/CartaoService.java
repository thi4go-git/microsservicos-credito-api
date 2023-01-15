package net.ddns.cloudtecnologia.cartoes.service;

import net.ddns.cloudtecnologia.cartoes.model.entity.Cartao;

import java.util.List;

public interface CartaoService {
    Cartao save(Cartao cartao);

    List<Cartao> getCartoesRendaMenorIgual(Long renda);
}
