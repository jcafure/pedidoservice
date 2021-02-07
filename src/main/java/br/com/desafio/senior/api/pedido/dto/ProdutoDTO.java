package br.com.desafio.senior.api.pedido.dto;

import br.com.desafio.senior.api.pedido.domain.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO implements Serializable {

    private UUID id;
    @NotBlank(message = "O campo nome não pode ser vazio.")
    @NotNull(message = "O campo nome não poder seu null")
    private String nome;
    @NotBlank(message = "O campo descricao não pode ser vazio.")
    @NotNull(message = "O campo descricao não poder seu null")
    private String descricao;

    @NotNull(message = "O campo valor não poder ser vazio")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valor;

    private Tipo tipo;
    private boolean ativo;
}
