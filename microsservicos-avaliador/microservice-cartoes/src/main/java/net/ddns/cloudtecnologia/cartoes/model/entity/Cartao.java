package net.ddns.cloudtecnologia.cartoes.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import net.ddns.cloudtecnologia.cartoes.rest.dto.CartaoDTO;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    //
    public Cartao(CartaoDTO dto) {
        this.nome = dto.getNome();
        this.bandeira = dto.getBandeira();
        this.renda = dto.getRenda();
        this.limiteBasico = dto.getLimiteBasico();
    }

}
