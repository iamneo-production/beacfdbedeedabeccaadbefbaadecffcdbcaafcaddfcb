package com.example;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class HouseManagementRestApiApplicationTests {

	@Autowired
    private MockMvc mockMvc;	

	//Add A New House
	@Test
    public void test_case1() throws Exception {
		
		String dataOne = "{\"houseId\":\"12881\",\"houseNo\":\"003\",\"status\":\"Not Occupied\",\"type\":\"2BHK\"}";
	 	mockMvc.perform(MockMvcRequestBuilders.post("/saveHouse")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.content(dataOne)
	 			.accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk())
	        	.andReturn();
	 	
    }
	
	
	//Get All Houses
	@Test
    public void test_case2() throws Exception {
		
	 	mockMvc.perform(MockMvcRequestBuilders.get("/getAllHouse")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$[*].houseNo").exists())
		        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
	        	.andReturn();
	 	
    }
	
	//Get A House By ID
	@Test
	public void test_case3() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/getHouse")
				.param("id","12881")
				.contentType(MediaType.APPLICATION_JSON)
		 		.accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.houseNo").value("003"))
		        .andExpect(jsonPath("$.status").value("Not Occupied"))
		        .andExpect(jsonPath("$.type").value("2BHK"))
		        .andReturn();
			
	}
	
	//Get A House By Type
	@Test
	public void test_case4() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/getByType")
				.param("type","2BHK")
				.contentType(MediaType.APPLICATION_JSON)
		 		.accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$[*].houseNo").exists())
		        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
		        .andReturn();
			
	}
	
	//Delete A House
	@Test
	public void test_case5() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/deleteHouse")
				.param("id","12881")
				.contentType(MediaType.APPLICATION_JSON)
		 		.accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andReturn();
			
	}


}
