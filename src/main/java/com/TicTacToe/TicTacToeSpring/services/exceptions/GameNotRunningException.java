package com.TicTacToe.TicTacToeSpring.services.exceptions;

public class GameNotRunningException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public GameNotRunningException() {
		super("The game is not running");
	}
}
