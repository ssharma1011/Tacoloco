package com.tacoloco.config;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "menu")
public class DataProperties {

	private HashMap<String, Double> menuData;

	public HashMap<String, Double> getMenuData() {
		return menuData;
	}

	public void setMenuData(HashMap<String, Double> menuData) {
		this.menuData = menuData;
	}

}
