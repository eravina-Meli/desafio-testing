package com.example.demo.desafiotesting.repository.impl;

import com.example.demo.desafiotesting.dto.NeighborhoodDTO;
import com.example.demo.desafiotesting.repository.INeighborhoodRepository;
import com.example.demo.desafiotesting.service.impl.HouseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class NeighborhoodRepositoryImplTest {

    //Testeamos que el archivo exista en el repositorio
    @Autowired
    INeighborhoodRepository iNeighborhoodRepository;

    private NeighborhoodDTO neighborhoodDTO;

    @Test
    void testGetNeighborhoodForNameExists() {
        neighborhoodDTO = new NeighborhoodDTO("Jacinto Vera",1000.0);
        Assertions.assertEquals(neighborhoodDTO.getName(),iNeighborhoodRepository.getNeighborhoodForName("Jacinto Vera").getName());
    }
    //Testeamos que el barrio NO exista en el repositorio
    @Test
    void testGetNeighborhoodForNameNotExists() {
        neighborhoodDTO = new NeighborhoodDTO("Jacinto Veraa",1000.0);
        Assertions.assertNotEquals(neighborhoodDTO,iNeighborhoodRepository.getNeighborhoodForName("Jacinto Vera").getName());
    }
}