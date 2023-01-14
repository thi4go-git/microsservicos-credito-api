package net.ddns.cloudtecnologia.msclientes.service;

import net.ddns.cloudtecnologia.msclientes.model.entity.Cliente;
import net.ddns.cloudtecnologia.msclientes.rest.dto.ClienteDTO;

import java.util.Optional;

public interface ClienteService {
    Cliente save(ClienteDTO dto);
    Optional<Cliente> getByCpf(String cpf);
}
