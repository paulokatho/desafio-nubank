package spring.boot.desafio.nubank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContatoDTO {

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;
    @NotBlank(message = "Email é obrigatório")
    private String email;
    private Long clienteId;
}
