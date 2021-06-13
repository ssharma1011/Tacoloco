package com.tacoloco.service;

import java.util.List;
import java.util.Map;

public interface OrderService {

	public Double process(List<Map<String, Object>> order) throws Exception;
	
}
