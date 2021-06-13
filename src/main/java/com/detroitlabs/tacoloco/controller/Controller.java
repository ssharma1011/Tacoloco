package com.detroitlabs.tacoloco.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.detroitlabs.tacoloco.common.Constants;
import com.detroitlabs.tacoloco.config.ValidationException;
import com.detroitlabs.tacoloco.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class Controller {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private OrderService orderService;

	/**
	 * This method is used to calculate the total amount for the items based upon
	 * their price and quantity requested!
	 * 
	 * @param order
	 * @return
	 */
	@ApiOperation(value = "This method is used to calculate the total amount for the items based upon\n"
			+ "	their price and quantity requested")
	@PostMapping(value = Constants.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> orderProcessing(@RequestBody Object order) {
		ResponseEntity<String> responseEntity = null;
		try {
			log.info(Constants.REQUEST_RECEIVED, objectMapper.writeValueAsString(order));
			List<Map<String, Object>> orderMapList = (List<Map<String, Object>>) order;
			Double total = orderService.process(orderMapList);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(Constants.FINAL_TOTAL + total);
		} catch (ValidationException e) {
			log.error(e);
			responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getExceptionReason());
		} catch (Exception e) {
			log.error(e);
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return responseEntity;
	}

}
