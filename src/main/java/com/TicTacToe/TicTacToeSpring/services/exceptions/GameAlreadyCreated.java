package com.TicTacToe.TicTacToeSpring.services.exceptions;

public class GameAlreadyCreated extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GameAlreadyCreated() {
		super("The game is already created");
	}
}
