package com.qantas.service.rest.exception;

public class AirportBadRequestException extends Exception {
	
	static final long serialVersionUID = 338723235454777L;

    public AirportBadRequestException() {
        super();
    }


    public AirportBadRequestException(String message) {
        super(message);
    }

  
    public AirportBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

  
    public AirportBadRequestException(Throwable cause) {
        super(cause);
    }

   
}