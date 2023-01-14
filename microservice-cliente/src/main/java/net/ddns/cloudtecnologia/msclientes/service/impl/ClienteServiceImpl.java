package net.ddns.cloudtecnologia.msclientes.service.impl;

import net.ddns.cloudtecnologia.msclientes.model.entity.Cliente;
import net.ddns.cloudtecnologia.msclientes.model.repository.ClienteRepository;
import net.ddns.cloudtecnologia.msclientes.rest.dto.ClienteDTO;
import net.ddns.cloudtecnologia.msclientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {


    @Autowired
    private ClienteRepository repository;


    @Override
    @Transactional
    public Cliente save(ClienteDTO dto) {
        return repository.save(new Cliente(dto));
    }

    @Override
    @Transactional
    public Optional<Cliente> getByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
