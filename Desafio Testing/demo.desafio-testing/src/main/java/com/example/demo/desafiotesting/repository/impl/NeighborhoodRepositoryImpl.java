package com.example.demo.desafiotesting.repository.impl;

import com.example.demo.desafiotesting.dto.NeighborhoodDTO;
import com.example.demo.desafiotesting.exception.NotFoundRoomException;
import com.example.demo.desafiotesting.repository.INeighborhoodRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class NeighborhoodRepositoryImpl implements INeighborhoodRepository {

    List<NeighborhoodDTO> neighborhoods;

    public NeighborhoodRepositoryImpl() {
        this.neighborhoods = loadFromDB();
    }

    //Levantamos la información del archivo donde tenemos todos los barrios con sus precios.
    private List<NeighborhoodDTO> loadFromDB() {
        List<NeighborhoodDTO> ret = null;

        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:neighborhood.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        var objectMapper = new ObjectMapper();

        try {
            ret = objectMapper.readValue(file, new TypeReference<List<NeighborhoodDTO>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    //Método que va a buscar un barrio por su nombre, devuelve el primero que encuentre, sino lanza una excepcion.
    @Override
    public NeighborhoodDTO getNeighborhoodForName(String name) throws NotFoundRoomException {
        return this.neighborhoods.stream()
                .filter(barrio -> barrio.getName().equals(name))
                .findFirst().orElseThrow(NotFoundRoomException::new);
    }
}
