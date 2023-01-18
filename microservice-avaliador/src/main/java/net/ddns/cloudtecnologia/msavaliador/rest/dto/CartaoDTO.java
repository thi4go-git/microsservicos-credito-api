package net.ddns.cloudtecnologia.msavaliador.rest.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoDTO {
    private Long id;
    private String nome;
    private String bandeira;
    private BigDecimal limiteBasico;
}