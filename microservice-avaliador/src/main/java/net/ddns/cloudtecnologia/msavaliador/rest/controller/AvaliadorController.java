package net.ddns.cloudtecnologia.msavaliador.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliador")
@Slf4j
public class AvaliadorController {


    @GetMapping
    public String status() {
        log.info("Obtendo status do Microserviço Avaliador.");
        return "Microsserviços Avaliador Rodando!";
    }


}
