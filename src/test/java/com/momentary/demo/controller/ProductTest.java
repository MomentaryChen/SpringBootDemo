package com.momentary.demo.controller;



import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.momentary.demo.SpringbootDemoApplication;

import ch.qos.logback.core.status.Status;
import net.minidev.json.JSONObject;
import static  org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = SpringbootDemoApplication.class)  
@AutoConfigureMockMvc
public class ProductTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup(){
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetProduct() throws Exception {
		HttpHeaders httpHeaders =new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/products")
				.headers(httpHeaders);
		
		ObjectWriter objwrite = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("data"))
				.andReturn();
//                .andExpect(content().string(equalTo("Hello World of Spring Boot")));
		
		System.out.println(objwrite.writeValueAsString(result));
	}

}
