package net.ddns.cloudtecnologia.msavaliador.rest.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoAprovadoDTO {
    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;
}