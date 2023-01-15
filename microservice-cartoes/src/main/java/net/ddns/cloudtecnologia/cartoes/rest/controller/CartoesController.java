package net.ddns.cloudtecnologia.cartoes.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
@Slf4j
public class CartoesController {

    @GetMapping("/status")
    public String status() {
        log.info("Obtendo status do Microserviço de Cartões.");
        return "Microsserviços Cartões Rodando!";
    }

}
