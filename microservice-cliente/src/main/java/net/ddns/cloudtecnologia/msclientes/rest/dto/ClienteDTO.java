package net.ddns.cloudtecnologia.msclientes.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO {
    private String cpf;
    private String nome;
    private Integer idade;
}
