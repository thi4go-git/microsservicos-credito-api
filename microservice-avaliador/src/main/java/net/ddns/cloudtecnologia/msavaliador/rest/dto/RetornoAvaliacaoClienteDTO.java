package net.ddns.cloudtecnologia.msavaliador.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoClienteDTO {
    private List<CartaoAprovadoDTO> cartoes;
}