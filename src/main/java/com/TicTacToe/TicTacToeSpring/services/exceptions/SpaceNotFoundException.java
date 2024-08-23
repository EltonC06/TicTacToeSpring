package com.TicTacToe.TicTacToeSpring.services.exceptions;

public class SpaceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public SpaceNotFoundException() {
		super("Space number not found.");
	}
}
