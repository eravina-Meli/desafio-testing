package com.example.demo.desafiotesting.controller;

import com.example.demo.desafiotesting.dto.HouseDTO;
import com.example.demo.desafiotesting.dto.response.HouseRoomsResponseDTO;
import com.example.demo.desafiotesting.dto.RoomDTO;
import com.example.demo.desafiotesting.exception.NotFoundRoomException;
import com.example.demo.desafiotesting.service.IHouseService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ControllerHouse {

    @Autowired
    private IHouseService iHouseService;

    /* Endopoint que va a mostrar el total de metros cuadros de la casa. Vale aclarar que dentro de HouseDTO contenemos una clase
    que pertenece al barrio con su nombre y precio */
    @PostMapping("/home/totalMt2")
    public Double gentCountMt2Property(@RequestBody @Valid HouseDTO casa) throws NotFoundRoomException {
        return iHouseService.getMt2Total(casa);
    }

    //Endpoint que va a indicar el valor de una propiedad a partir de sus habitaciones y medidas
    @PostMapping(value = "/home/value")
    public ResponseEntity<Double> getValueProperty(@RequestBody @Valid HouseDTO casa) throws NotFoundRoomException {
        Double value = iHouseService.getValueProperty(casa);
        return new ResponseEntity<>(value,HttpStatus.OK);
    }

    //Endpoint que va a indicar cual es la habitación mas grande
    @PostMapping("/home/biggestRoom")
    public ResponseEntity<RoomDTO> getBiggestRoom(@RequestBody @Valid HouseDTO casa) throws NotFoundRoomException {
       RoomDTO roomDto = iHouseService.getBiggestRoom(casa);
       return new ResponseEntity<>(roomDto, HttpStatus.OK);
    }

    //Endpoint que va a determinar la cantidad de metros cuadrados que tiene cada habitación de una propiedad
    @PostMapping("/home/getMt2ForHouse")
    public ResponseEntity<HouseRoomsResponseDTO> getMt2ForRoomsHouse(@RequestBody @Valid HouseDTO casa) throws NotFoundRoomException {
        HouseRoomsResponseDTO houseRoomsResponseDTO = iHouseService.getMt2ForRoomsHouse(casa);
        return new ResponseEntity<>(houseRoomsResponseDTO, HttpStatus.OK);
    }
}
