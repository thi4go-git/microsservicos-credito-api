package net.ddns.cloudtecnologia.msavaliador.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.CartaoClienteDTO;
import net.ddns.cloudtecnologia.msavaliador.rest.dto.DadosClienteDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SituacaoCliente {
    private DadosClienteDTO cliente;
    private List<CartaoClienteDTO> cartoes;
}