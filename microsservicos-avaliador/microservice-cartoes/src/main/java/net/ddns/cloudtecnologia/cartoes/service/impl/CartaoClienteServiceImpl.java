package net.ddns.cloudtecnologia.cartoes.service.impl;


import net.ddns.cloudtecnologia.cartoes.model.entity.CartaoCliente;
import net.ddns.cloudtecnologia.cartoes.model.repository.CartaoClienteRepository;
import net.ddns.cloudtecnologia.cartoes.service.CartaoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartaoClienteServiceImpl implements CartaoClienteService {

    @Autowired
    private CartaoClienteRepository repository;

    @Override
    public List<CartaoCliente> listCartoesByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    @Override
    public CartaoCliente save(CartaoCliente cartaoCliente) {
        return repository.save(cartaoCliente);
    }
}
