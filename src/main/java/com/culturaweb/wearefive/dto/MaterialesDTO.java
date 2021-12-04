package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialesDTO {
    private List<MaterialResponseDTO> materiales;
}
