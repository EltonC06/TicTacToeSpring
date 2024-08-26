package com.TicTacToe.TicTacToeSpring.services.exceptions;

public class PositionNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PositionNotFoundException(Integer space) {
		super("The position [" + space + "]  was not found");
	}
}
