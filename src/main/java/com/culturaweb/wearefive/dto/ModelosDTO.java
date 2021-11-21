package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelosDTO {
    private List<ModeloZapatoEnviadoDTO> modeloZapatos;
}
