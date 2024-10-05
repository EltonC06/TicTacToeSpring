package com.TicTacToe.TicTacToeSpring.services.exceptions;

public class GameAlreadyCreatedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GameAlreadyCreatedException() {
		super("The game is already created");
	}
}
