package com.example.demo.desafiotesting.repository;

import com.example.demo.desafiotesting.dto.NeighborhoodDTO;
import com.example.demo.desafiotesting.exception.NotFoundRoomException;

public interface INeighborhoodRepository {
    NeighborhoodDTO getNeighborhoodForName(String name) throws NotFoundRoomException;
}
