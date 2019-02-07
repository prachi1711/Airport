package com.qantas.service.rest.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qantas.service.rest.model.Airport;
import com.qantas.service.rest.exception.AirportServerException;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AirportResponseTransformerTest {
	
	@Autowired
	private AirportResponseTransformer objectToTest;
	
	private List<Airport> expectedAirportsList = null;
	
	@Before
	public void setUp() {
		objectToTest = new AirportResponseTransformer();
		expectedAirportsList = new ArrayList<Airport>();
		Airport airport = new Airport();
		airport.setCode("BZD");
		expectedAirportsList.add(airport);
		airport = new Airport();
		airport.setCode("NBS");
		expectedAirportsList.add(airport);
	}
	
	@Test
	public void testTransformSuccessResponseReturnsOK() throws Exception {
		String response = createTestSuccessResponse();
		List<Airport> airports = objectToTest.transformResponse(response);
		assertTrue(airports.size()==2);
		assertEquals(airports.get(0).getCode(), expectedAirportsList.get(0).getCode());
	}
	
	@Test(expected=AirportServerException.class)
	public void testTransformInvalidResponseThrowsException() throws Exception {
		String response = createTestInvalidResponse();
		objectToTest.transformResponse(response);
	}
	
	@Test
	public void testTransformEmptyResponseReturnsEmptyList() throws Exception {
		String response = createTestEmptyResponse();
		List<Airport> airports = objectToTest.transformResponse(response);
		assertTrue(airports.size()==0);
	}
	
	@Test(expected=AirportServerException.class)
	public void testTransformNoResponseThrowsException() throws Exception {
		String response = createTestNoResponse();
		objectToTest.transformResponse(response);
	}
	
	private String createTestSuccessResponse() {
		String responseString = "{\"airports\":[{\"code\":\"BZD\",\"display_name\":\"Balranald\",\"international_airport\":false,"
				+ " \"regional_airport\":false,\"location\":{\"latitude\":-34.616665,\"longitude\":143.61667},"
				+ " \"currency_code\":\"AUD\",\"timezone\":\"Australia/Sydney\","
				+ " \"country\":{\"code\":\"AU\",\"display_name\":\"Australia\"}},"
		        + " {\"code\":\"NBS\",\"display_name\":\"Changbaishan\",\"international_airport\":false,\"regional_airport\":false,"
				+ " \"location\":{\"latitude\":42.066666,\"longitude\":127.816666},\"currency_code\":\"CNY\","
				+ " \"timezone\":\"Asia/Shanghai\",\"country\":{\"code\":\"CN\",\"display_name\":\"China\"}}]}";
		return responseString;
	}
	
	private String createTestInvalidResponse() {
		String responseString = "{[{\"code\":\"BZD\",\"display_name\":\"Balranald\",\"international_airport\":false,"
				+ " \"regional_airport\":false,\"location\":{\"latitude\":-34.616665,\"longitude\":143.61667},"
				+ " \"currency_code\":\"AUD\",\"timezone\":\"Australia/Sydney\","
				+ " \"country\":{\"code\":\"AU\",\"display_name\":\"Australia\"}},"
		        + " {\"code\":\"NBS\",\"display_name\":\"Changbaishan\",\"international_airport\":false,\"regional_airport\":false,"
				+ " \"location\":{\"latitude\":42.066666,\"longitude\":127.816666},\"currency_code\":\"CNY\","
				+ " \"timezone\":\"Asia/Shanghai\",\"country\":{\"code\":\"CN\",\"display_name\":\"China\"}}]}";
		return responseString;
	}
	
	private String createTestEmptyResponse() {
		String responseString = "{\"airports\":[]}";
		return responseString;
	}
	
	private String createTestNoResponse() {
		String responseString = "";
		return responseString;
	}
}