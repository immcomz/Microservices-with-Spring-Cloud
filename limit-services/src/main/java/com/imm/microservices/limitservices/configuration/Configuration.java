package com.imm.microservices.limitservices.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//initialise as a bean
@Component 
//initialise as a application properties of "limits-service"
@ConfigurationProperties("limits-service")
public class Configuration {
	private int min;
	private int max;
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	

}
 