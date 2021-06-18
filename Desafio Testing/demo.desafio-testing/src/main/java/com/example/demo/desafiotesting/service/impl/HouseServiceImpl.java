package com.example.demo.desafiotesting.service.impl;

import com.example.demo.desafiotesting.dto.*;
import com.example.demo.desafiotesting.dto.response.HouseRoomsResponseDTO;
import com.example.demo.desafiotesting.dto.response.RoomsMt2ResponseDTO;
import com.example.demo.desafiotesting.exception.NotFoundRoomException;
import com.example.demo.desafiotesting.repository.INeighborhoodRepository;
import com.example.demo.desafiotesting.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseServiceImpl implements IHouseService {

    //Inyección de dependencia del barrio repository
    @Autowired
    private INeighborhoodRepository iNeighborhoodRepository;

    //Método que llama al repository para calcular los metros cuadrados de una propiedad
    @Override
    public Double getMt2Total(HouseDTO casa) throws NotFoundRoomException {
        //Verificamos que el barrio exista en el repositorio
        boolean neighborhood = getNeighborhood(casa);
        if(neighborhood) {
            //Calculamos mediante lambda, los metros cuadrados de la habitación para la propiedad.
            double mt2 =
                    casa.getRooms()
                            .stream()
                            .mapToDouble(room -> room.getRoomLength() * room.getRoomWidth())
                            .sum();
            return mt2;
        }else{
            throw new NotFoundRoomException("El barrio no es el mismo.",HttpStatus.BAD_GATEWAY);
        }
    }

    //Método que va a calcular el valor total de una propiedad
    @Override
    public Double getValueProperty(HouseDTO casa) throws NotFoundRoomException {
        if(getNeighborhood(casa)) {
            double mt2 = calculateMt2(casa) * casa.getNeighborhood().getPrice();
            return mt2;
        }else{
            throw new NotFoundRoomException("El barrio no es el mismo.",HttpStatus.NOT_FOUND);
        }
    }

    //Métdo que va a devolver la habitación más grande
    @Override
    public RoomDTO getBiggestRoom(HouseDTO casa) throws NotFoundRoomException {
        if(getNeighborhood(casa)) {
            RoomDTO roomBiggest = null;
            double maxRoom = 0;
            for (RoomDTO roomDTO : casa.getRooms()) {
                Double mt2ForRoom = getMt2ForRoom(roomDTO);
                if (mt2ForRoom > maxRoom) {
                    roomBiggest = roomDTO;
                    maxRoom = mt2ForRoom;
                }
            }
            return roomBiggest;
        }else{
            throw new NotFoundRoomException("El barrio no es el mismo.",HttpStatus.NOT_FOUND);
        }
    }

    //Método que va a retornar los metros cuadrados de cada habitación.
    @Override
    public HouseRoomsResponseDTO getMt2ForRoomsHouse(HouseDTO casa) throws NotFoundRoomException {
            boolean neighborhood = getNeighborhood(casa);
            if(neighborhood) {
                HouseRoomsResponseDTO houseRoomsResponseDTO = new HouseRoomsResponseDTO();
                houseRoomsResponseDTO.setPropName(casa.getPropName());
                houseRoomsResponseDTO.setRooms(getListRoomsDTO(casa));
                return houseRoomsResponseDTO;
            }else {
                throw new NotFoundRoomException("El barrio no es el mismo.", HttpStatus.NOT_FOUND);
            }
    }

    //Método que va a devolver las Habitaciones con su metro cuadrado.
    public List<RoomsMt2ResponseDTO> getListRoomsDTO(HouseDTO casa){
        List<RoomsMt2ResponseDTO> rooms = new ArrayList<>();
        RoomsMt2ResponseDTO roomsMt2ResponseDTO;
        //Recorremos casa habitación de la casa y vamos creando el DTO a retornar con el nombre de la habitacion y el total de Mt2.
        for (RoomDTO roomDTO : casa.getRooms()){
            roomsMt2ResponseDTO = new RoomsMt2ResponseDTO(roomDTO.getRoomName(),getMt2ForRoom(roomDTO));
            rooms.add(roomsMt2ResponseDTO);
        }
        return rooms;
    }

    //Método que retorna los metros cuadrados por habitación.
    public double getMt2ForRoom(RoomDTO roomDTO){
        double mt2Room =0;
        mt2Room = roomDTO.getRoomLength() * roomDTO.getRoomWidth();
        return mt2Room;
    }

    //Método que retorna los metros cuadrados totales de una casa.
    public double calculateMt2(HouseDTO casa) throws RuntimeException{
        double mt2 = 0;
        mt2 =
                casa.getRooms()
                        .stream()
                        .mapToDouble(room -> room.getRoomLength() * room.getRoomWidth())
                        .sum();
        return mt2;
    }

    //Buscamos el barrio en el repositorio.
    public boolean getNeighborhood(HouseDTO casa){
        try {
            NeighborhoodDTO neighborhood = iNeighborhoodRepository.getNeighborhoodForName(casa.getNeighborhood().getName());
            return neighborhood.getName().equals(casa.getNeighborhood().getName()) &&
                    neighborhood.getPrice() == casa.getNeighborhood().getPrice();
        }catch (Exception e){
            throw new NotFoundRoomException("El barrio no existe en el repositorio.", HttpStatus.NOT_FOUND);
        }
    }
}


