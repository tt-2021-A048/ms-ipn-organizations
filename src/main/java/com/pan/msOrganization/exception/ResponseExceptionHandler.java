package com.pan.msOrganization.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseExceptionHandler.class);
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Ocurrio un error",ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, String> errors = new HashMap<String, String>();
		String errorMessagesForLog = "";
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
				
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			
			errors.put(fieldName, errorMessage);
		});
		
		for (Entry<String, String> msg : errors.entrySet()) {
			errorMessagesForLog += msg.getKey() + ", " + msg.getValue() + " | ";
		}
		
		logger.trace("mensaje de rastreo");
		logger.debug("Error Validacion en Microservicio de Organizaciones: ");
		logger.info("mensaje de INFORMACION: " + errorMessagesForLog);
		logger.warn("mensaje de advertencia");
		logger.error("mensaje de error");
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Ocurrio un error",ex.getMessage(), errors);
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
}
