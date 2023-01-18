package net.ddns.cloudtecnologia.msavaliador.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {
    private List<CartaoAprovado> cartoes;
}