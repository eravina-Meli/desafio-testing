package com.example.demo.desafiotesting.service;

import com.example.demo.desafiotesting.dto.HouseDTO;
import com.example.demo.desafiotesting.dto.response.HouseRoomsResponseDTO;
import com.example.demo.desafiotesting.dto.RoomDTO;
import com.example.demo.desafiotesting.exception.NotFoundRoomException;

public interface IHouseService {
    Double getMt2Total(HouseDTO casa) throws NotFoundRoomException;

    Double getValueProperty(HouseDTO casa) throws NotFoundRoomException;

    RoomDTO getBiggestRoom(HouseDTO casa) throws NotFoundRoomException;

    HouseRoomsResponseDTO getMt2ForRoomsHouse(HouseDTO casa) throws NotFoundRoomException;
}
