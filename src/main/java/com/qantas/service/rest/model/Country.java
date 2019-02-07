package com.qantas.service.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true) 
public class Country {
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("display_name")
	private String displayName;
	
	public Country() {
		
	}
	
	public Country(String code, String displayName) {
		this.code = code;
		this.displayName = displayName;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	
}