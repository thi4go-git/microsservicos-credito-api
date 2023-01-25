package net.ddns.cloudtecnologia.msavaliador.client;


import net.ddns.cloudtecnologia.msavaliador.rest.dto.DadosClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


// @FeignClient(name = "ClienteResourceClient", url = "https://viacep.com.br/ws")
@FeignClient(value = "microservice-cliente", path = "/clientes")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DadosClienteDTO> dadosCliente(@RequestParam("cpf") String cpf);
}