package com.TicTacToe.TicTacToeSpring.services.exceptions;

public class GameNotCreatedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public GameNotCreatedException() {
		super("Game not created yet");
	}
}
