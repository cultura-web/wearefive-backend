package com.culturaweb.wearefive.dto;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
public class QAsDTO {
    private List<QAResponseDTO> qas;
}
