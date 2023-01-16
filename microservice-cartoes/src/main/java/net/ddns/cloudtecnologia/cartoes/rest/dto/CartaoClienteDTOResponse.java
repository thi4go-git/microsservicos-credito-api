package net.ddns.cloudtecnologia.cartoes.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ddns.cloudtecnologia.cartoes.model.entity.Cartao;
import net.ddns.cloudtecnologia.cartoes.model.entity.CartaoCliente;


import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartaoClienteDTOResponse {
    private String nome;
    private Cartao cartao;
    private BigDecimal limite;

    //
    public static CartaoClienteDTOResponse fromModel(CartaoCliente model) {
        return new CartaoClienteDTOResponse(
                model.getCartao().getNome(),
                model.getCartao(),
                model.getLimite()
        );
    }
}
