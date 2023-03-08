package com.arthur.digimonbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DigimonDto {
    @NotBlank
    private String name;

    private String level;
}
