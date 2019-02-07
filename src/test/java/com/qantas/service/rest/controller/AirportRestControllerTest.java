package com.qantas.service.rest.controller;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qantas.service.rest.client.QantasClient;
import com.qantas.service.rest.model.Airport;
import com.qantas.service.rest.model.AirportTypeEnum;
import com.qantas.service.rest.model.Country;
import com.qantas.service.rest.model.Location;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AirportRestControllerTest {
	
	private AirportRestController objectToTest;
	
	@Mock
	private QantasClient mockClient;
	
	private List<Airport> airports = null;
	
	@Before
	public void setUp() {
		airports = new ArrayList<Airport>();
    	Airport airport = new Airport();
		airport.setCode("BZD");
		airport.setDisplayName("Balranald");
		airport.setLocation(new Location(-34.616665, 143.61667));
		airport.setCurrencyCode("AUD");
		airport.setTimezone("Australia/Sydney");
		airport.setCountry(new Country("AU", "Australia"));
		airport.setInternationalAirport(false);
		airport.setRegionalAirport(false);
		airports.add(airport);
		
		airport = new Airport();
		airport.setCode("BZE");
		airport.setDisplayName("Belize");
		airport.setLocation(new Location(17.533333, -88.3));
		airport.setCurrencyCode("AUD");
		airport.setTimezone("Australia/Sydney");
		airport.setCountry(new Country("BZ", "Belize"));
		airport.setInternationalAirport(false);
		airport.setRegionalAirport(false);
		airports.add(airport);
		
		airport = new Airport();
		airport.setCode("CBR");
		airport.setDisplayName("Canberra");
		airport.setLocation(new Location(-35.3, 149.18333));
		airport.setCurrencyCode("AUD");
		airport.setTimezone("Australia/Sydney");
		airport.setCountry(new Country("AU", "Australia"));
		airport.setInternationalAirport(false);
		airport.setRegionalAirport(true);
		airports.add(airport);
		
		airport = new Airport();
		airport.setCode("CCK");
		airport.setDisplayName("Cocos Island");
		airport.setLocation(new Location(-12.183333, 96.816666));
		airport.setCurrencyCode("AUD");
		airport.setTimezone("Indian/Cocos");
		airport.setCountry(new Country("CC", "Cocos Keeling Isl"));
		airport.setInternationalAirport(true);
		airport.setRegionalAirport(true);
		airports.add(airport);
		
		airport = new Airport();
		airport.setCode("CCU");
		airport.setDisplayName("Kolkata");
		airport.setLocation(new Location(22.65, 88.45));
		airport.setCurrencyCode("INR");
		airport.setTimezone("Asia/Calcutta");
		airport.setCountry(new Country("IN", "India"));
		airport.setInternationalAirport(true);
		airport.setRegionalAirport(false);
		airports.add(airport);
		
		when(mockClient.getAllAirports()).thenReturn(airports);
		
		objectToTest = new AirportRestController(mockClient);
		
	}
	
	@Test
	public void testGetAllAirportsSuccessfully() {
		List<Airport> response = objectToTest.getAirports(null, null, null);
		assertTrue(response.size()==5);
	}
	
	@Test
	public void testFilterAirportsListBasedOnAirportCodeExistsReturnsFilteredList() {
		List<Airport> response = objectToTest.getAirports(null, "BZD", null);
		assertTrue(response.size()==1);
	}
	
	@Test
	public void testFilterAirportsListBasedOnCountryCodeExistsReturnsFilteredList() {
		List<Airport> response = objectToTest.getAirports("AU", null, null);
		assertTrue(response.size()==2);
	}
	
	@Test
	public void testFilterAirportsListBasedOnDomesticAirportExistsReturnsFilteredList() {
		List<Airport> response = objectToTest.getAirports(null, null, AirportTypeEnum.DOM);
		assertTrue(response.size()==2);
	}
	
	@Test
	public void testFilterAirportsListBasedOnInternationalAirportExistsReturnsFilteredList() {
		List<Airport> response = objectToTest.getAirports(null, null, AirportTypeEnum.INT);
		assertTrue(response.size()==2);
	}
	
	@Test
	public void testFilterAirportsListBasedOnBothAirportTypeExistsReturnsFilteredList() {
		List<Airport> response = objectToTest.getAirports(null, null, AirportTypeEnum.BOTH);
		assertTrue(response.size()==1);
	}
	
	@Test
	public void testFilterAirportsListBasedOnAnyAirportTypeExistsReturnsFilteredList() {
		List<Airport> response = objectToTest.getAirports(null, null, AirportTypeEnum.ANY);
		assertTrue(response.size()==5);
	}
	
	@Test
	public void testFilterAirportsListBasedOnAirportAndCountryCodeExistsReturnsFilteredList() {
		List<Airport> response = objectToTest.getAirports("AU", "BZD", null);
		assertTrue(response.size()==1);
	}
	
	@Test
	public void testFilterAirportsListBasedOnAirportCodeAndAirportTypeExistsReturnsFilteredList() {
		List<Airport> response = objectToTest.getAirports(null, "CCK", AirportTypeEnum.BOTH);
		assertTrue(response.size()==1);
	}
	
	@Test
	public void testFilterAirportsListBasedOnAllFiltersExistsReturnsFilteredList() {
		List<Airport> response = objectToTest.getAirports("AU", "BZD", AirportTypeEnum.ANY);
		assertTrue(response.size()==1);
	}
	
	@Test
	public void filterAirportsListBasedOnAirportCodeDoesNotExistsReturnsEmptyList() {
		List<Airport> response = objectToTest.getAirports("AU", "BZE", null);
		assertTrue(response.size()==0);
	}
	
	@Test
	public void filterAirportsListBasedOnCountryCodeDoesNotExistsReturnsEmptyList() {
		List<Airport> response = objectToTest.getAirports("AUZ", "BZD", AirportTypeEnum.ANY);
		assertTrue(response.size()==0);
	}
	
	@Test
	public void filterAirportsListBasedOnDomesticAirportDoesNotExistsReturnsEmptyList() {
		List<Airport> response = objectToTest.getAirports("AU", "BZD", AirportTypeEnum.DOM);
		assertTrue(response.size()==0);
	}
	
	@Test
	public void whenDownstreamServiceReturnEmptyResponseReturnNull() {
		when(mockClient.getAllAirports()).thenReturn(null);
		List<Airport> response = objectToTest.getAirports("AU", "BZD", AirportTypeEnum.ANY);
		assertNull(response);
	}
	
}
