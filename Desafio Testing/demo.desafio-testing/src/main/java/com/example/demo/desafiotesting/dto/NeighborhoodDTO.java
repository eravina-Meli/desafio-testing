package com.example.demo.desafiotesting.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NeighborhoodDTO {

    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String name;

    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @Max(value = 4000, message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private double price;
}
