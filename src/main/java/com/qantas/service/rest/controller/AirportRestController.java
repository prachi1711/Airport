package com.qantas.service.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qantas.service.rest.client.QantasClient;
import com.qantas.service.rest.model.Airport;
import com.qantas.service.rest.model.AirportTypeEnum;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AirportRestController {
	
	@Autowired
	private QantasClient client;

	/**
	 * Get the list of airports from Qantas and allow user to filter the list based on below params
	 * @param countryCode string - country code
	 * @param airportCode string - airport code
	 * @param airportType enum - allowed values BOTH, ANY, DOM (Domestic), INT (International)
	 * @return list of airports based on the specified criteria
	 */
	@GetMapping("/airports")
    public List<Airport>  getAirports(@RequestParam(value = "country_code", required = false) String countryCode,
            @RequestParam(value = "airport_code" , required = false) String airportCode,
            @RequestParam(value = "airport_type" , required = false) AirportTypeEnum airportType) {
		
		
		List<Airport> airports = client.getAllAirports();
		
		if(airports != null && airports.size() > 0 
				&& (airportCode != null || countryCode != null || airportType != null)) {

			airports = airports.stream()
	                .filter(p -> {
	                	if(airportCode == null || airportCode.equalsIgnoreCase("")) {
	                		return true;
	                	} else if(p.getCode().equalsIgnoreCase(airportCode)) {
	                        return true;
	                    } 
	                    return false;
	                }) .filter(p -> {
	                	if(countryCode == null || countryCode.equalsIgnoreCase("")) {
	                		return true;
	                	} else if(p.getCountry().getCode().equalsIgnoreCase(countryCode)) {
	                        return true;
	                    } 
	                    return false;
	                }) .filter(p -> {
	                	if(airportType == null || airportType.equals(AirportTypeEnum.ANY)) {
	                		return true;
	                	} else if (airportType.equals(AirportTypeEnum.BOTH) && p.isInternationalAirport()
	                			&& p.isRegionalAirport()) {
	                		return true;
	                	} else if(airportType.equals(AirportTypeEnum.INT) && p.isInternationalAirport()) {
	                        return true;
	                    } else if(airportType.equals(AirportTypeEnum.DOM) && p.isRegionalAirport()) {
	                        return true;
	                    } 
	                    return false;
	                })
	                .collect(Collectors.toList());
		}
		
        return airports;
    }
	
}