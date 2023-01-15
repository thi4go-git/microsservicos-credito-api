package net.ddns.cloudtecnologia.msclientes.rest.controller;

import lombok.extern.slf4j.Slf4j;
import net.ddns.cloudtecnologia.msclientes.model.entity.Cliente;
import net.ddns.cloudtecnologia.msclientes.rest.dto.ClienteDTO;
import net.ddns.cloudtecnologia.msclientes.service.impl.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@Slf4j// Acesso aos log
public class ClienteController {

    @Autowired
    private ClienteServiceImpl service;

    @GetMapping("/status")
    public String status() {
        log.info("Obtendo status do Microserviço de clientes.");
        return "Microsserviços de Cliente Rodando!";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteDTO dto) {
        Cliente cliente = service.save(dto);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }


    @GetMapping("{cpf}")
    public ResponseEntity getByCpf(@RequestParam String cpf) {
        var cliente = service.getByCpf(cpf);
        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);

    }

}

