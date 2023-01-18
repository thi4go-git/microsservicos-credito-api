package net.ddns.cloudtecnologia.msavaliador.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.ddns.cloudtecnologia.msavaliador.model.entity.CartaoAprovado;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoClienteDTO {
    private List<CartaoAprovado> cartoes;
}