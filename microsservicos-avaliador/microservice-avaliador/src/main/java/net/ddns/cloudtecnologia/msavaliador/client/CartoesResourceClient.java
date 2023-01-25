package net.ddns.cloudtecnologia.msavaliador.client;

import net.ddns.cloudtecnologia.msavaliador.rest.dto.CartaoClienteDTO;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.CartaoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "microservice-cartoes", path = "/cartoes")
public interface CartoesResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoClienteDTO>> getCartoesByCliente(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    ResponseEntity<List<CartaoDTO>> getCartoesRendaAteh(@RequestParam("renda") Long renda);

}
