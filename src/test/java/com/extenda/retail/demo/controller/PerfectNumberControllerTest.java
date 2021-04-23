package com.extenda.retail.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.extenda.retail.demo.common.ApplicationConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@WebMvcTest(value = PerfectNumberController.class)
public class PerfectNumberControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void givenPerfectNumber_whenCheckPerfectNumber_thenReturnMessage() throws Exception{
		
		String URI = "/rest/api/checkPerfectNumber/28";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String output = response.getContentAsString();
		
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		Assertions.assertEquals(ApplicationConstants.PERFECT_NUMBER_MESSAGE, output);
		
	}
	
	@Test
	public void givenNotAPerfectNumber_whenCheckPerfectNumber_thenReturnMessage() throws Exception{
		
		String URI = "/rest/api/checkPerfectNumber/42";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String output = response.getContentAsString();
		
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		Assertions.assertEquals(ApplicationConstants.NOT_A_PERFECT_NUMBER_MESSAGE, output);
		
	}
	
	@Test
	public void givenValidRange_whenGetPerfectNumbers_thenReturnList() throws Exception{
		
		String URI = "/rest/api/getAllPerfectNumbers?start=1&end=1000";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		ObjectMapper mapper = new ObjectMapper();
		String[] numbers = mapper.readValue(response.getContentAsString(), String[].class);
		
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		Assertions.assertEquals(3, numbers.length);
		
	}
	
	@Test
	public void givenInvalidRange_whenGetPerfectNumbers_thenReturnBlankList() throws Exception{
		
		String URI = "/rest/api/getAllPerfectNumbers?start=&end=1000";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		ObjectMapper mapper = new ObjectMapper();
		String[] numbers = mapper.readValue(response.getContentAsString(), String[].class);
		
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		Assertions.assertEquals(0, numbers.length);
		
	}

}
