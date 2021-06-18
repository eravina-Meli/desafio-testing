package com.example.demo.desafiotesting.service.impl;

import com.example.demo.desafiotesting.dto.*;
import com.example.demo.desafiotesting.dto.response.HouseRoomsResponseDTO;
import com.example.demo.desafiotesting.dto.response.RoomsMt2ResponseDTO;
import com.example.demo.desafiotesting.exception.NotFoundRoomException;
import com.example.demo.desafiotesting.repository.INeighborhoodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HouseServiceImplTest {

    @Mock
    INeighborhoodRepository iNeighborhoodRepository;

    @InjectMocks
    HouseServiceImpl houseService;

    private HouseDTO houseDTO;
    private List<RoomDTO> rooms;
    private NeighborhoodDTO neighborhoodDTO;
    private List<RoomsMt2ResponseDTO> listRoomsDTO;

    //Método donde vamos a inicializar los datos
    @BeforeEach
    void setUp(){
        houseDTO = new HouseDTO();
        rooms = new ArrayList<>();
        listRoomsDTO = new ArrayList<>();
        //barrio
        neighborhoodDTO = new NeighborhoodDTO();
        neighborhoodDTO.setName("Jacinto Vera");
        neighborhoodDTO.setPrice(1000.0);
        when(iNeighborhoodRepository.getNeighborhoodForName("Jacinto Vera")).thenReturn(neighborhoodDTO);
    }


    //Testeamos el total de metros cuadrados de la propiedad
    @Test
    void testGetMt2TotalForHouseCalculated() {
        when(iNeighborhoodRepository.getNeighborhoodForName("Jacinto Vera")).thenReturn(neighborhoodDTO);
        //Habitacinoes
        RoomDTO roomDTO_1=  new RoomDTO("Habitacion_1", 10.0, 2.0);
        RoomDTO roomDTO_2 = new RoomDTO("Habitacion_2", 8.0, 2.0);
        rooms.add(roomDTO_1);
        rooms.add(roomDTO_2);
        //Casa
        houseDTO.setPropName("Casa 1");
        houseDTO.setNeighborhood(neighborhoodDTO);
        houseDTO.setRooms(rooms);
        Assertions.assertEquals(36.0,houseService.getMt2Total(houseDTO));
    }

    //Testeamos que si el valor del price es diferente, debe lanzar una exepción.
    @Test
    void testGetValuePropertyForHouseCalculatedException() {
        NeighborhoodDTO barrio2 = new NeighborhoodDTO("Jacinto Vera",500.0);
        //Habitacinoes
        RoomDTO roomDTO_1=  new RoomDTO("Habitacion_1", 10.0, 2.0);
        RoomDTO roomDTO_2 = new RoomDTO("Habitacion_2", 8.0, 2.0);
        rooms.add(roomDTO_1);
        rooms.add(roomDTO_2);
        //Casa
        houseDTO.setPropName("Casa 1");
        houseDTO.setNeighborhood(barrio2);
        houseDTO.setRooms(rooms);
        Assertions.assertThrows(NotFoundRoomException.class,() ->houseService.getMt2Total(houseDTO));
    }

    //Testeamos el precio total de la propiedad en base a sus características.
    @Test
    void testGetValuePropertyForHouseCalculated() {
        //Habitacinoes
        RoomDTO roomDTO_1=  new RoomDTO("Habitacion_1", 10.0, 2.0);
        RoomDTO roomDTO_2 = new RoomDTO("Habitacion_2", 8.0, 2.0);
        rooms.add(roomDTO_1);
        rooms.add(roomDTO_2);
        //Casa
        houseDTO.setPropName("Casa 1");
        houseDTO.setNeighborhood(neighborhoodDTO);
        houseDTO.setRooms(rooms);
        Assertions.assertEquals(36000,houseService.getValueProperty(houseDTO));
    }

    @Test
    void testValuePriceForNeighborhoodWithToRepositoryException() {
        NeighborhoodDTO barrio2 = new NeighborhoodDTO("Jacinto Vera",500.0);
        //Habitacinoes
        RoomDTO roomDTO_1=  new RoomDTO("Habitacion_1", 10.0, 2.0);
        RoomDTO roomDTO_2 = new RoomDTO("Habitacion_2", 8.0, 2.0);
        rooms.add(roomDTO_1);
        rooms.add(roomDTO_2);
        //Casa
        houseDTO.setPropName("Casa 1");
        houseDTO.setNeighborhood(barrio2);
        houseDTO.setRooms(rooms);
        Assertions.assertThrows(NotFoundRoomException.class,() ->houseService.getValueProperty(houseDTO));
    }

    @Test
    void testGetBiggestRoomNotFoundNeighborhoodException() {
        NeighborhoodDTO barrio2 = new NeighborhoodDTO("Jacinto Vera",500.0);
        //Habitacinoes
        RoomDTO roomDTO_1=  new RoomDTO("Habitacion_1", 10.0, 2.0);
        RoomDTO roomDTO_2 = new RoomDTO("Habitacion_2", 8.0, 2.0);
        rooms.add(roomDTO_1);
        rooms.add(roomDTO_2);
        //Casa
        houseDTO.setPropName("Casa 1");
        houseDTO.setNeighborhood(barrio2);
        houseDTO.setRooms(rooms);
        Assertions.assertThrows(NotFoundRoomException.class,() ->houseService.getBiggestRoom(houseDTO));
    }

    @Test
    void testGetMt2ForRoomsHouseNotFoundNeighborhoodException() {
        NeighborhoodDTO barrio2 = new NeighborhoodDTO("Jacinto Vera",500.0);
        //Habitacinoes
        RoomDTO roomDTO_1=  new RoomDTO("Habitacion_1", 10.0, 2.0);
        RoomDTO roomDTO_2 = new RoomDTO("Habitacion_2", 8.0, 2.0);
        rooms.add(roomDTO_1);
        rooms.add(roomDTO_2);
        //Casa
        houseDTO.setPropName("Casa 1");
        houseDTO.setNeighborhood(barrio2);
        houseDTO.setRooms(rooms);
        Assertions.assertThrows(NotFoundRoomException.class,() ->houseService.getMt2ForRoomsHouse(houseDTO));
    }

    //Testeamos que el barrio exista en el repositorio
    @Test
    void testgetMt2ForRoomsHouseExist() {
        Assertions.assertEquals("Jacinto Vera",iNeighborhoodRepository.getNeighborhoodForName("Jacinto Vera").getName());
    }
    //Testeamos que el barrio NO exista en el repositorio
    @Test
    void testgetMt2ForRoomsHouseNotExist() {
        Assertions.assertNotEquals("Jacinto Veraa",iNeighborhoodRepository.getNeighborhoodForName("Jacinto Vera").getName());
    }

    //Testeaamos método que obtiene la habitación con mayor tamaño.
    @Test
    void testGetBiggestRoomMaxValue() {
        //Habitacinoes
        RoomDTO roomDTO_1=  new RoomDTO("Habitacion_1", 10.0, 2.0);
        RoomDTO roomDTO_2 = new RoomDTO("Habitacion_2", 8.0, 2.0);
        rooms.add(roomDTO_1);
        rooms.add(roomDTO_2);
        //Casa
        houseDTO.setPropName("Casa 1");
        houseDTO.setNeighborhood(neighborhoodDTO);
        houseDTO.setRooms(rooms);

        Assertions.assertEquals(roomDTO_1,houseService.getBiggestRoom(houseDTO));
    }


    //Verificamos que el total de metros cuadrado por ambiente sea correcto.
    @Test
    void testVerifyGetMt2ForHouse() {
        //Habitacinoes
        RoomDTO dto_1 = new RoomDTO("Habitacion_1", 10.0,2.0);
        RoomDTO dto_2 = new RoomDTO("Habitacion_2", 8.0,2.0);
        rooms.add(dto_1);
        rooms.add(dto_2);
        //Casa
        houseDTO.setPropName("Casa 1");
        houseDTO.setNeighborhood(neighborhoodDTO);
        houseDTO.setRooms(rooms);

        //DTO a mostrar
        RoomsMt2ResponseDTO roomDTO_1=  new RoomsMt2ResponseDTO("Habitacion_1", 20.0);
        RoomsMt2ResponseDTO roomDTO_2 = new RoomsMt2ResponseDTO("Habitacion_2", 16.0);
        listRoomsDTO.add(roomDTO_1);
        listRoomsDTO.add(roomDTO_2);

        HouseRoomsResponseDTO houseRoomsResponseDTO =
                new HouseRoomsResponseDTO(
                        "Casa 1",
                        listRoomsDTO
                );

        Assertions.assertTrue(()->listRoomsDTO.equals(houseService.getMt2ForRoomsHouse(houseDTO).getRooms()));
    }

    @Test
    void testVerifyGetMt2ForHouseNotEquals() {
        //Habitacinoes
        RoomDTO dto_1 = new RoomDTO("Habitacion_1", 10.0,2.0);
        RoomDTO dto_2 = new RoomDTO("Habitacion_2", 8.0,3.0);
        rooms.add(dto_1);
        rooms.add(dto_2);
        //Casa
        houseDTO.setPropName("Casa 1");
        houseDTO.setNeighborhood(neighborhoodDTO);
        houseDTO.setRooms(rooms);

        //DTO a mostrar
        RoomsMt2ResponseDTO roomDTO_1=  new RoomsMt2ResponseDTO("Habitacion_1", 20.0);
        RoomsMt2ResponseDTO roomDTO_2 = new RoomsMt2ResponseDTO("Habitacion_2", 16.0);
        listRoomsDTO.add(roomDTO_1);
        listRoomsDTO.add(roomDTO_2);

        HouseRoomsResponseDTO houseRoomsResponseDTO =
                new HouseRoomsResponseDTO(
                        "Casa 1",
                        listRoomsDTO
                );

        Assertions.assertFalse(()->listRoomsDTO.equals(houseService.getMt2ForRoomsHouse(houseDTO).getRooms()));
    }

    @Test
    void testValueNeighborhoodWithToRepositoryNotFounda() {
        NeighborhoodDTO barrio2 = new NeighborhoodDTO("Jacinto Varaa",1000.0);
        //Habitacinoes
        RoomDTO roomDTO_1=  new RoomDTO("Habitacion_1", 10.0, 2.0);
        RoomDTO roomDTO_2 = new RoomDTO("Habitacion_2", 8.0, 2.0);
        rooms.add(roomDTO_1);
        rooms.add(roomDTO_2);
        //Casa
        houseDTO.setPropName("Casa 1");
        houseDTO.setNeighborhood(barrio2);
        houseDTO.setRooms(rooms);

        when(iNeighborhoodRepository.getNeighborhoodForName("Jacinto Veraa")).thenThrow(NotFoundRoomException.class);
        Assertions.assertThrows(NotFoundRoomException.class,() -> houseService.getNeighborhood(houseDTO));
    }
}