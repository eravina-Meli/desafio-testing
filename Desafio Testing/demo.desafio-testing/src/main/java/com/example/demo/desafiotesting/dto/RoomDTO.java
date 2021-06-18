package com.example.demo.desafiotesting.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    @NotBlank(message = "El nombre de la habitación no puede estar vacío.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la Habitación debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String roomName;

    @NotNull(message = "El ancho de la habitación no puede estar vacío.")
    @DecimalMax(value = "25.0", message = "El máximo ancho permitido por propiedad es de 25 mts.")
    @DecimalMin(value = "0.0", message = "El mínimo ancho permitido por propiedad es de 0 mts.")
    private Double roomWidth;

    @NotNull(message = "El largo de la habitación no puede estar vacío.")
    @DecimalMax(value = "33.0", message = "El máximo ancho permitido por propiedad es de 33 mts.")
    @DecimalMin(value = "0.0", message = "El mínimo ancho permitido por propiedad es de 0 mts.")
    private Double roomLength;
}
