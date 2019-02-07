package com.qantas.service.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class Airport {
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("display_name")
	private String displayName;
	
	@JsonProperty("international_airport")
	private boolean internationalAirport;

	@JsonProperty("regional_airport")
	private boolean regionalAirport;
	
	@JsonProperty("location")
	private Location location;
	
	@JsonProperty("currency_code")
	private String currencyCode;
	
	@JsonProperty("timezone")
	private String timezone;
	
	@JsonProperty("country")
	private Country country;
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public boolean isInternationalAirport() {
		return internationalAirport;
	}

	public void setInternationalAirport(boolean internationalAirport) {
		this.internationalAirport = internationalAirport;
	}

	public boolean isRegionalAirport() {
		return regionalAirport;
	}

	public void setRegionalAirport(boolean regionalAirport) {
		this.regionalAirport = regionalAirport;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currency_code) {
		this.currencyCode = currency_code;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	
}