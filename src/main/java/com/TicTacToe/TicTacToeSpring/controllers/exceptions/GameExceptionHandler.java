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
import com.TicTacToe.TicTacToeSpring.services.exceptions.OccupiedSpaceException;
import com.TicTacToe.TicTacToeSpring.services.exceptions.SpaceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GameExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(GameNotCreatedException.class)
	public ResponseEntity<Object> handleGameNotFound(GameNotCreatedException exception, HttpServletRequest request) {
		String error = "Game not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(GameAlreadyCreated.class)
	public ResponseEntity<Object> handleGameAlreadyCreated(GameAlreadyCreated exception, HttpServletRequest request) {
		String error = "The game is already created";
		HttpStatus status = HttpStatus.CONFLICT;
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(GameNotRunningException.class)
	public ResponseEntity<Object> handleGameNotRunning(GameNotRunningException exception, HttpServletRequest request) {
		String error = "Game not running";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(OccupiedSpaceException.class)
	public ResponseEntity<Object> handleOccupiedSpace(OccupiedSpaceException exception, HttpServletRequest request) {
		String error = "Space occupied";
		HttpStatus status = HttpStatus.CONFLICT;
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(SpaceNotFoundException.class)
	public ResponseEntity<Object> handleSpaceNotFound(SpaceNotFoundException exception, HttpServletRequest request) {
		String error = "Space not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
}
