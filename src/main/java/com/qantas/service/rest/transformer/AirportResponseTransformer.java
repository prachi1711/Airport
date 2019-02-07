package com.qantas.service.rest.transformer;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qantas.service.rest.model.AirportResponse;

import lombok.NoArgsConstructor;

import com.qantas.service.rest.exception.AirportServerException;
import com.qantas.service.rest.model.Airport;

@Component
@NoArgsConstructor
public class AirportResponseTransformer {
	
	/**
	 * This method helps to transform the response received from Qantas to a valid response required by the client
	 * @param response string response received from Qantas
	 * @return transformed list of airports expected by the client
	 * @throws AirportServerException if there are any exceptions parsing the response received from Qantas
	 */
	public List<Airport> transformResponse(String response) throws AirportServerException  {
		List<Airport> airports = null;
		
		try {
			AirportResponse airportResponse = new ObjectMapper().readValue(response, AirportResponse.class);
			if(airportResponse != null) {
				airports = airportResponse.getAirports();
			}
		} catch (IOException e) {
			throw new AirportServerException(e.getMessage());
		}
		return airports;
	}
}