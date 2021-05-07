package com.orangetalents.viacep.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		var trouble = new Trouble();
		trouble.setStatus(status.value());
		trouble.setDataHora(LocalDateTime.now());
		trouble.setTitulo("Usuário não existe");
		return super.handleExceptionInternal(ex, trouble, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		var trouble = new Trouble();
		trouble.setStatus(404);
		trouble.setDataHora(LocalDateTime.now());
		trouble.setTitulo("Usuário não existe");
		return super.handleExceptionInternal(ex, trouble, headers, status, request);
	}
	
}
