package net.ddns.cloudtecnologia.msclientes.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.ddns.cloudtecnologia.msclientes.rest.dto.ClienteDTO;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String cpf;
    @Column
    private String nome;
    @Column
    private Integer idade;

    public Cliente(ClienteDTO dto) {
        this.cpf = dto.getCpf();
        this.nome = dto.getNome();
        this.idade = dto.getIdade();
    }
}
