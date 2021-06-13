package com.tacoloco.common;

public class Constants {

	public static final String BASE_URL = "/order/total";

	public static final String MENU_ITEM_NOT_AVAILABLE_ERROR = "Menu Item is not present in the request";
	public static final String QUANTITY_LESS_THAN_ONE_ERROR = "Quantity cannot be less than 1. Please try again!";
	public static final String EMPTY_ORDER_ERROR = "Order is empty. Please try again";
	public static final String EXCEPTION_OCCURRED = "Exception Occurred {}";

	public static final String DISCOUNT_ELIGIBLE = "This Order is eligible for discount of 20 percent";
	public static final String FINAL_TOTAL = "Final Total for the Order is: $";

	public static final String QUANTITY = "quantity";
	public static final String NAME = "name";
	public static final String REQUEST_RECEIVED = "Order Request Recieved {}";

	public static final String MODEL_EXAMPLE = "[\n" + "    {\n" + "        \"name\": \"Veggie Taco\",\n"
			+ "        \"quantity\":1\n" + "    },\n" + "    {\n" + "        \"name\": \"Chicken Taco\",\n"
			+ "        \"quantity\":3\n" + "    }\n" + "]";

}
