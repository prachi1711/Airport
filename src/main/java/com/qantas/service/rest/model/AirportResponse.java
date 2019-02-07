package com.qantas.service.rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qantas.service.rest.model.Airport;

public class AirportResponse {

	@JsonProperty("airports")
	private List<Airport> airports;

	public List<Airport> getAirports() {
		return airports;
	}

	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}

}