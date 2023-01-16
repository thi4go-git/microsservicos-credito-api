package net.ddns.cloudtecnologia.cartoes.rest.controller;

import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import net.ddns.cloudtecnologia.cartoes.model.entity.Cartao;
import net.ddns.cloudtecnologia.cartoes.model.entity.CartaoCliente;
import net.ddns.cloudtecnologia.cartoes.rest.dto.CartaoClienteDTOResponse;
import net.ddns.cloudtecnologia.cartoes.rest.dto.CartaoDTO;
import net.ddns.cloudtecnologia.cartoes.service.CartaoService;
import net.ddns.cloudtecnologia.cartoes.service.impl.CartaoClienteServiceImpl;
import net.ddns.cloudtecnologia.cartoes.service.impl.CartaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartoes")
@Slf4j
public class CartoesController {

    @Autowired
    private CartaoServiceImpl cartaoService;

    @Autowired
    private CartaoClienteServiceImpl cartaoClienteService;

    @GetMapping
    public String status() {
        log.info("Obtendo status do Microserviço de Cartões.");
        return "Microsserviços Cartões Rodando!";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoDTO dto) {
        var cartao = cartaoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda) {
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoClienteDTOResponse>> getCartoesByCliente
            (@RequestParam("cpf") String cpf) {
        List<CartaoCliente> lista = cartaoClienteService.listCartoesByCpf(cpf);
        //
        List<CartaoClienteDTOResponse> listResp = lista.stream()
                .map(CartaoClienteDTOResponse::fromModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(listResp);
    }
}
