package com.detroitlabs.tacoloco.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detroitlabs.tacoloco.common.Constants;
import com.detroitlabs.tacoloco.config.DataProperties;
import com.detroitlabs.tacoloco.config.ValidationException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private DataProperties dataProperties;

	@Override
	public Double process(List<Map<String, Object>> orderMapList) throws Exception {
		Double total = 0.0;
		Integer finalQuantity = 0;
		try {
			validateRequest(orderMapList);
			for (Map<String, Object> orderMap : orderMapList) {
				Integer localQuantity = 0;
				localQuantity = Objects.isNull(orderMap.get(Constants.QUANTITY)) ? 0
						: (Integer) orderMap.get(Constants.QUANTITY);
				finalQuantity = finalQuantity + localQuantity;
				total = total + dataProperties.getMenuData().get(orderMap.get(Constants.NAME)) * localQuantity;
			}

			if (finalQuantity >= 4) {
				log.info(Constants.DISCOUNT_ELIGIBLE);
				total = applyDiscount(total);
			}
			log.info(Constants.FINAL_TOTAL + total);

		} catch (ValidationException e) {
			log.error(Constants.EXCEPTION_OCCURRED, e);
			throw e;
		} catch (Exception e) {
			log.error(Constants.EXCEPTION_OCCURRED, e);
			throw e;
		}

		return total;
	}

	/**
	 * This method is used to apply discount of 20% for order above quantity of 4!
	 * 
	 * @param total
	 * @return
	 */
	private Double applyDiscount(Double total) {
		total = total - (total * (0.2));
		return total;
	}

	/**
	 * This Method validates the request based on size and menu item if present or
	 * not!
	 * 
	 * @param order
	 * @return
	 */
	private void validateRequest(List<Map<String, Object>> orderMapList) {
		if (orderMapList.isEmpty()) {
			throw new ValidationException(Constants.EMPTY_ORDER_ERROR);
		}

		Integer quantity = 0;
		for (Map<String, Object> orderMap : orderMapList) {
			if (Objects.nonNull(orderMap.get(Constants.QUANTITY))) {
				quantity = quantity + (Integer) orderMap.get(Constants.QUANTITY);
			}

			if (Objects.nonNull(orderMap.get(Constants.NAME))
					&& !dataProperties.getMenuData().containsKey(orderMap.get(Constants.NAME))) {
				log.error("Sorry, Requested item " + orderMap.get(Constants.NAME) + " is not in our menu");
				throw new ValidationException(
						"Sorry, Requested item " + orderMap.get(Constants.NAME) + " is not in our menu");
			}

			if (Objects.isNull(orderMap.get(Constants.NAME))) {
				throw new ValidationException(Constants.MENU_ITEM_NOT_AVAILABLE_ERROR);
			}
		}
		if (quantity < 1) {
			throw new ValidationException(Constants.QUANTITY_LESS_THAN_ONE_ERROR);
		}
	}

}
