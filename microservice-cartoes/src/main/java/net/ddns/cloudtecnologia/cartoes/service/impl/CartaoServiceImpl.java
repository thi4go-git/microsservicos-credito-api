package net.ddns.cloudtecnologia.cartoes.service.impl;

import net.ddns.cloudtecnologia.cartoes.model.entity.Cartao;
import net.ddns.cloudtecnologia.cartoes.model.repository.CartaoRepository;
import net.ddns.cloudtecnologia.cartoes.rest.dto.CartaoDTO;
import net.ddns.cloudtecnologia.cartoes.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartaoServiceImpl implements CartaoService {

    @Autowired
    private CartaoRepository repository;

    @Transactional
    @Override
    public Cartao save(CartaoDTO dto) {
        return repository.save(new Cartao(dto));
    }

    @Transactional
    @Override
    public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
        var rendaBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(rendaBigDecimal);
    }

    @Override
    public Optional<Cartao> findById(Long id) {
        return repository.findById(id);
    }
}
