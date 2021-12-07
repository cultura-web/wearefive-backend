package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QAResponseDTO {

    private Integer id;
    private String pregunta;
    private String respuesta;
}
