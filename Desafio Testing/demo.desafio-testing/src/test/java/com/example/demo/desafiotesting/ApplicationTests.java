package com.example.demo.desafiotesting;

import com.example.demo.desafiotesting.dto.HouseDTO;
import com.example.demo.desafiotesting.dto.NeighborhoodDTO;
import com.example.demo.desafiotesting.dto.RoomDTO;
import com.example.demo.desafiotesting.dto.response.HouseRoomsResponseDTO;
import com.example.demo.desafiotesting.dto.response.RoomsMt2ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private HouseDTO houseDTO;
	private List<RoomDTO> rooms;
	private NeighborhoodDTO neighborhoodDTO;

	@BeforeEach
	void setUp(){
		houseDTO = new HouseDTO();
		rooms = new ArrayList<>();
		//barrio
		neighborhoodDTO = new NeighborhoodDTO();
		neighborhoodDTO.setName("Jacinto Vera");
		neighborhoodDTO.setPrice(1000.0);
		rooms = Arrays.asList(
				new RoomDTO("Habitacion1",10.0,2.0),
				new RoomDTO("Habitacion2", 8.0,2.0));
	}

	@Test
	void testGetCountMt2PropertyOutput() throws Exception {
		houseDTO = new HouseDTO("Casa 1",neighborhoodDTO,rooms);
		double responseDTO =36.0;

		ObjectWriter writer = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE,false)
				.writer();

		String payloadJson = writer.writeValueAsString(houseDTO);
		String responseJson = writer.writeValueAsString(responseDTO);

		MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/home/totalMt2")
		.contentType(MediaType.APPLICATION_JSON)
		.content(payloadJson))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andReturn();

		Assertions.assertEquals(responseJson,response.getResponse().getContentAsString());
	}

	@Test
	void testGetValuePropertyOutPut() throws Exception{
		houseDTO = new HouseDTO("Casa 1",neighborhoodDTO,rooms);
		double responseDTO = 36000.0;

		ObjectWriter writer = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE,false)
				.writer();

		String payloadJson = writer.writeValueAsString(houseDTO);
		String responseJson = writer.writeValueAsString(responseDTO);

		MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/home/value")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(payloadJson))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andReturn();

		Assertions.assertEquals(responseJson,response.getResponse().getContentAsString());
	}
	@Test
	void testGetBiggestRoomOutPut() throws Exception{
		houseDTO = new HouseDTO("Casa 1",neighborhoodDTO,rooms);
		RoomDTO responseDTO = new RoomDTO("Habitacion1",10.0,2.0);

		ObjectWriter writer = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE,false)
				.writer();

		String payloadJson = writer.writeValueAsString(houseDTO);
		String responseJson = writer.writeValueAsString(responseDTO);

		MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/home/biggestRoom")
		.contentType(MediaType.APPLICATION_JSON)
		.content(payloadJson))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andReturn();

		Assertions.assertEquals(responseJson,response.getResponse().getContentAsString());
	}

	@Test
	void testGetMt2ForRoomsHouse()throws Exception{
		houseDTO = new HouseDTO("Casa 1",neighborhoodDTO,rooms);
		HouseRoomsResponseDTO responseDTO = new HouseRoomsResponseDTO(
				"Casa 1",
				Arrays.asList(new RoomsMt2ResponseDTO("Habitacion1",20.0),
						new RoomsMt2ResponseDTO("Habitacion2",16.0))
		);

		ObjectWriter writer = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE,false)
				.writer();

		String payloadJson = writer.writeValueAsString(houseDTO);
		String responseJson = writer.writeValueAsString(responseDTO);

		MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/home/getMt2ForHouse")
		.contentType(MediaType.APPLICATION_JSON)
		.content(payloadJson))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andReturn();

		Assertions.assertEquals(responseJson, response.getResponse().getContentAsString());
	}

}
