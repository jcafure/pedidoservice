package br.com.desafio.senior.api.pedido.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public enum Tipo implements Serializable {

    PRODUTO (0, "Produto"),
    SERVICO (1, "Servico");

    private Integer id;
    private String valor;

    public static Integer toEnum(Integer tipo) {
        if (tipo == null) {
            return null;
        }
        for (Tipo categoria: Tipo.values()) {
            if (tipo.equals(categoria.id)){
                return categoria.id;
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + tipo);
    }

}
