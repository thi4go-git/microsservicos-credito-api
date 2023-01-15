package net.ddns.cloudtecnologia.cartoes.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ddns.cloudtecnologia.cartoes.model.entity.BandeiraCartao;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartaoDTO {
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;
}
