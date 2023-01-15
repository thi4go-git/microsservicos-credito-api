package net.ddns.cloudtecnologia.cartoes.rest.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.ddns.cloudtecnologia.cartoes.model.entity.BandeiraCartao;

import javax.persistence.Entity;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartaoDTO {
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;
}
