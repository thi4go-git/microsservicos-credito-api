package net.ddns.cloudtecnologia.cartoes.service;

import net.ddns.cloudtecnologia.cartoes.model.entity.CartaoCliente;

import java.util.List;

public interface CartaoClienteService {

    List<CartaoCliente> listCartoesByCpf(String cpf);

    CartaoCliente save(CartaoCliente cartaoCliente);
}
