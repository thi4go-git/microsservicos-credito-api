package net.ddns.cloudtecnologia.msavaliador.rest.controller;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import net.ddns.cloudtecnologia.msavaliador.exception.DadosClienteNotFoundException;
import net.ddns.cloudtecnologia.msavaliador.exception.ErroComunicacaoMicroservicesException;
import net.ddns.cloudtecnologia.msavaliador.model.entity.SituacaoCliente;
import net.ddns.cloudtecnologia.msavaliador.service.Impl.AvaliadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliador")
@Slf4j
public class AvaliadorController {

    @Autowired
    private AvaliadorServiceImpl avaliadorService;


    @GetMapping
    public String status() {
        log.info("Obtendo status do Microserviço de Avaliação.");
        return "Microsserviços Avaliação Rodando!";
    }


    @GetMapping(value = "situacao", params = "cpf")
    public ResponseEntity consultarSituacaoCliente(@RequestParam("cpf") String cpf) {
        try {
            SituacaoCliente situacaoCliente = avaliadorService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        } catch (RetryableException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


}
