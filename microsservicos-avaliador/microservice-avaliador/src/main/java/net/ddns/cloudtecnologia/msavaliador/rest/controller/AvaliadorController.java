package net.ddns.cloudtecnologia.msavaliador.rest.controller;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import net.ddns.cloudtecnologia.msavaliador.exception.DadosClienteNotFoundException;
import net.ddns.cloudtecnologia.msavaliador.exception.ErroComunicacaoMicroservicesException;
import net.ddns.cloudtecnologia.msavaliador.exception.ErroSolicitacaoCartaoException;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.ProtocoloSolicitacaoCartaoDTO;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.DadosAvaliacaoDTO;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.DadosSolicitacaoEmissaoCartaoDTO;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.RetornoAvaliacaoClienteDTO;
import net.ddns.cloudtecnologia.msavaliador.model.entity.SituacaoCliente;
import net.ddns.cloudtecnologia.msavaliador.service.Impl.AvaliadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacaoDTO dados) {
        try {
            RetornoAvaliacaoClienteDTO avaliacaoCliente = avaliadorService
                    .realizarAvaliacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(avaliacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }


    @PostMapping("solicitacoes-cartao")
    public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartaoDTO dados) {
        try {
            ProtocoloSolicitacaoCartaoDTO solicitacaoCartaoDTO = avaliadorService
                    .solicitarEmissaoCartao(dados);
            return ResponseEntity.ok(solicitacaoCartaoDTO);
        } catch (ErroSolicitacaoCartaoException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
