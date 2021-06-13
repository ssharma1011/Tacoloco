package com.detroitlabs.tacoloco.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(SpringJUnit4ClassRunner.class)
public class ControllerExceptionTest {

	@InjectMocks
	Controller controller;

	@Mock
	ResponseEntity<String> responseEntity;

	@Mock
	ObjectMapper objectMapper;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void orderProcessingTest() throws Exception {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String request = prepareSuccessRequest();

		mockMvc.perform(post("/order/total").contentType(MediaType.APPLICATION_JSON_VALUE).content((request)))
				.andExpect(status().isInternalServerError());
	}

	/**
	 * 
	 * @return
	 */
	private String prepareSuccessRequest() {
		String request = "[\n" + "    {\n" + "        \"name\": \"Veggie Taco\",\n" + "        \"quantity\":5\n"
				+ "    },\n" + "    {\n" + "        \"name\": \"Chicken Taco\",\n" + "        \"quantity\":5\n"
				+ "    }\n" + "]";
		return request;
	}

}
