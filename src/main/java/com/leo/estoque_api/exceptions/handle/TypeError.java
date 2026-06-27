package com.leo.estoque_api.exceptions.handle;

import lombok.Getter;

@Getter
public enum TypeError {

    ENTITY_NOT_FOUND("Entidade não encontrada"),
    BUSINESS_ROLE_VIOLATION("Violação de Regra de Negócio"),
    SYSTEM_ERROR("Erro no servidor"),
    INVALID_DATA("Dados Inválidos"),
    INVALID_PARAMETER("Parâmetro Inválido"),
    INVALID_BODY("Corpo Inválido");

    private final String type;

    TypeError(String type) {
        this.type = type;
    }

}
