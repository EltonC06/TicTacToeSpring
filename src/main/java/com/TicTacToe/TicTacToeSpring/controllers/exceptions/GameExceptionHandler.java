package com.TicTacToe.TicTacToeSpring.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.TicTacToe.TicTacToeSpring.services.exceptions.GameAlreadyCreated;
import com.TicTacToe.TicTacToeSpring.services.exceptions.GameNotCreatedException;
import com.TicTacToe.TicTacToeSpring.services.exceptions.GameNotRunningException;
import com.TicTacToe.TicTacToeSpring.services.exceptions.OccupiedPositionException;
import com.TicTacToe.TicTacToeSpring.services.exceptions.PositionNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GameExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(GameNotCreatedException.class)
	public ResponseEntity<Object> handleGameNotFound(GameNotCreatedException exception, HttpServletRequest request) {
		String error = "Game finding error";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(GameAlreadyCreated.class)
	public ResponseEntity<Object> handleGameAlreadyCreated(GameAlreadyCreated exception, HttpServletRequest request) {
		String error = "Game creation error";
		HttpStatus status = HttpStatus.CONFLICT;
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(GameNotRunningException.class)
	public ResponseEntity<Object> handleGameNotRunning(GameNotRunningException exception, HttpServletRequest request) {
		String error = "Game running error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(OccupiedPositionException.class)
	public ResponseEntity<Object> handleOccupiedPosition(OccupiedPositionException exception, HttpServletRequest request) {
		String error = "Game position number error";
		HttpStatus status = HttpStatus.CONFLICT;
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(PositionNotFoundException.class)
	public ResponseEntity<Object> handlePositionNotFound(PositionNotFoundException exception, HttpServletRequest request) {
		String error = "Game position number error";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
}
