package com.example.demo.desafiotesting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomsMt2ResponseDTO {

    @NotBlank(message = "El nombre de la habitación no puede estar vacío.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la Habitación debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String roomName;
    private double totalMt2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomsMt2ResponseDTO that = (RoomsMt2ResponseDTO) o;
        return Double.compare(that.totalMt2, totalMt2) == 0 && Objects.equals(roomName, that.roomName);
    }
}
