package com.leo.estoque_api.model.enums;

import lombok.Getter;

@Getter
public enum TypeMovements {

    ENTRY("Entrada"),
    EXIT("Saída"),
    REGISTRATION("Registro"),
    DEACTIVATION("Desativação");

    private final String type;

    TypeMovements(String type) {
        this.type = type;
    }

}
