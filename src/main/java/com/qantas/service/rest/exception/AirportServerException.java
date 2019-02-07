package com.qantas.service.rest.exception;

public class AirportServerException extends RuntimeException {
	
	static final long serialVersionUID = 338723235454799L;

    public AirportServerException() {
        super();
    }


    public AirportServerException(String message) {
        super(message);
    }

  
    public AirportServerException(String message, Throwable cause) {
        super(message, cause);
    }

  
    public AirportServerException(Throwable cause) {
        super(cause);
    }
}