package com.qantas.service.rest.client;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.qantas.service.rest.exception.AirportServerException;
import com.qantas.service.rest.model.Airport;
import com.qantas.service.rest.model.AirportResponse;
import com.qantas.service.rest.model.Country;
import com.qantas.service.rest.model.Location;
import com.qantas.service.rest.transformer.AirportResponseTransformer;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QantasClientTest {
	
	@Autowired
	private QantasClient objectToTest;
	
	@Mock
	private RestTemplate mockRestTemplate ;
    
    @Autowired
	private AirportResponseTransformer transformer;
    
    @Before
    public void setUp() throws Exception {
    	
    	List<Airport> airports = new ArrayList<Airport>();
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
		
		AirportResponse expectedAirportResponse  = new AirportResponse();
		
		expectedAirportResponse.setAirports(airports);

		objectToTest = new QantasClient(transformer, mockRestTemplate);
    }

	@Test
    public void testGetAllAirportsSuccess() {
		when(mockRestTemplate.getForObject(objectToTest.QANTAS_URL, String.class)).thenReturn(createSuccessResponse());
        List<Airport> airports = objectToTest.getAllAirports();
        assertTrue(airports.size()==1);
 
    }
	
	@Test
    public void testReceiveEmptyResponseFromDownstreamSendEmptyList() {
		when(mockRestTemplate.getForObject(objectToTest.QANTAS_URL, String.class)).thenReturn(null);
        List<Airport> airports = objectToTest.getAllAirports();
        assertNull(airports);
 
    }
	
	@Test(expected=AirportServerException.class)
    public void testRestClientExceptionFromDownstreamReturnAirportException() {
		when(mockRestTemplate.getForObject(objectToTest.QANTAS_URL, String.class)).thenThrow(new RestClientException("client connection exception") );
        List<Airport> airports = objectToTest.getAllAirports();
        assertNull(airports);
    }
	
	@Test(expected=AirportServerException.class)
    public void testAnyRunTimeExceptionFromDownstreamReturnAirportException() {
		when(mockRestTemplate.getForObject(objectToTest.QANTAS_URL, String.class)).thenThrow(new RuntimeException("runtime exception") );
        List<Airport> airports = objectToTest.getAllAirports();
        assertNull(airports);
    }
	
	private String createSuccessResponse() {
		String responseString = "{\"airports\":[{\"code\":\"BZD\",\"display_name\":\"Balranald\",\"international_airport\":false,"
				+ " \"regional_airport\":false,\"location\":{\"latitude\":-34.616665,\"longitude\":143.61667},"
				+ " \"currency_code\":\"AUD\",\"timezone\":\"Australia/Sydney\","
				+ " \"country\":{\"code\":\"AU\",\"display_name\":\"Australia\"}}]}";
		return responseString;
	}
}