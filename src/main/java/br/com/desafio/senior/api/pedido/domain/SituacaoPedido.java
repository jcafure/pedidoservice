package br.com.desafio.senior.api.pedido.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public enum SituacaoPedido implements Serializable {

    ABERTA (0, "Aberto"),
    FECHADO(1, "Fechado");

    private Integer id;
    private String valor;
}
