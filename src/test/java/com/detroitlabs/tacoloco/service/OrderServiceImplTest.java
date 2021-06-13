package com.detroitlabs.tacoloco.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.detroitlabs.tacoloco.config.DataProperties;
import com.detroitlabs.tacoloco.config.ValidationException;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceImplTest {

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;

	@Mock
	private DataProperties dataProperties;

	@Test
	public void processTestWithSuccessScenario() throws Exception {

		List<Map<String, Object>> orderMapList = getOrderMapList();
		when(dataProperties.getMenuData()).thenReturn(getItemMenuMap());

		Double actual = orderServiceImpl.process(orderMapList);
		assertNotNull(actual);
		assertEquals(8.8, actual);
	}

	@Test(expected = ValidationException.class)
	public void processTestWithEmptyRequest() throws Exception {
		List<Map<String, Object>> orderMapList = new ArrayList<>();
		orderServiceImpl.process(orderMapList);
	}

	@Test(expected = Exception.class)
	public void processTestWithItemNotPresentInTheMenu() throws Exception {

		List<Map<String, Object>> orderMapList = getOrderMapList();
		Map<String, Object> eggTacoMap = new HashMap<>();
		eggTacoMap.put("name", "egg Taco");
		eggTacoMap.put("quantity", 2);
		orderMapList.add(eggTacoMap);

		when(dataProperties.getMenuData()).thenReturn(getItemMenuMap());

		orderServiceImpl.process(orderMapList);
	}

	@Test(expected = Exception.class)
	public void processTestWithQuantityZero() throws Exception {

		List<Map<String, Object>> orderMapList = getOrderMapListWithQuantityZero();
		when(dataProperties.getMenuData()).thenReturn(getItemMenuMap());

		orderServiceImpl.process(orderMapList);
	}

	@Test(expected = Exception.class)
	public void processTestWithItemNameNull() throws Exception {

		List<Map<String, Object>> orderMapList = getOrderMapList();
		Map<String, Object> eggTacoMap = new HashMap<>();
		eggTacoMap.put("quantity", 2);
		orderMapList.add(eggTacoMap);
		when(dataProperties.getMenuData()).thenReturn(getItemMenuMap());

		orderServiceImpl.process(orderMapList);
	}

	@Test(expected = Exception.class)
	public void processTestWithQuantityNull() throws Exception {

		List<Map<String, Object>> orderMapList = new ArrayList<>();
		Map<String, Object> veggieTacoMap = new HashMap<>();
		veggieTacoMap.put("name", "Veggie Taco");
		orderMapList.add(veggieTacoMap);
		when(dataProperties.getMenuData()).thenReturn(getItemMenuMap());

		orderServiceImpl.process(orderMapList);
	}

	private List<Map<String, Object>> getOrderMapList() {
		List<Map<String, Object>> orderMapList = new ArrayList<>();
		Map<String, Object> veggieTacoMap = new HashMap<>();
		veggieTacoMap.put("name", "Veggie Taco");
		veggieTacoMap.put("quantity", 2);
		Map<String, Object> chickenTacoMap = new HashMap<>();
		chickenTacoMap.put("name", "Chicken Taco");
		chickenTacoMap.put("quantity", 2);
		orderMapList.add(chickenTacoMap);
		orderMapList.add(veggieTacoMap);
		return orderMapList;
	}

	private List<Map<String, Object>> getOrderMapListWithQuantityZero() {
		List<Map<String, Object>> orderMapList = new ArrayList<>();
		Map<String, Object> veggieTacoMap = new HashMap<>();
		veggieTacoMap.put("name", "Veggie Taco");
		veggieTacoMap.put("quantity", 0);
		Map<String, Object> chickenTacoMap = new HashMap<>();
		chickenTacoMap.put("name", "Chicken Taco");
		chickenTacoMap.put("quantity", 0);
		orderMapList.add(chickenTacoMap);
		orderMapList.add(veggieTacoMap);
		return orderMapList;
	}

	private HashMap<String, Double> getItemMenuMap() {
		HashMap<String, Double> itemMenuMap = new HashMap<>();
		itemMenuMap.put("Veggie Taco", 2.5);
		itemMenuMap.put("Chicken Taco", 3.0);
		return itemMenuMap;
	}

}
