package com.qantas.service.rest.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.qantas.service.rest.exception.AirportServerException;
import com.qantas.service.rest.model.Airport;
import com.qantas.service.rest.transformer.AirportResponseTransformer;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class QantasClient {
	
	static final String QANTAS_URL = "https://www.qantas.com.au/api/airports";
	static final int TIMEOUT = 5000;
	
	@Autowired
	private AirportResponseTransformer airportResponseTransformer;
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * call Qantas API to get the list of airports
	 * @return list of airports
	 * @throws AirportServerException if any exception happens while calling Qantas or processing response from Qantas
	 */
	public List<Airport> getAllAirports() throws AirportServerException  {
		
		List<Airport> airports = null;
		
		try {
			 //TODO add timeout handling, connect and read timeout
			 String responseStr = restTemplate.getForObject(QANTAS_URL, String.class);
			 
			 if(responseStr != null && !responseStr.equalsIgnoreCase("")) {
				 airports = airportResponseTransformer.transformResponse(responseStr);
			 }
		} catch(RestClientException ex) {
			throw new AirportServerException(ex.getMessage());
		} catch(RuntimeException e) {
			throw new AirportServerException(e.getMessage());
		}
		
		 return airports;
	}
}