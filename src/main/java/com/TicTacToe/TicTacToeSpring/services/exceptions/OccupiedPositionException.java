package com.TicTacToe.TicTacToeSpring.services.exceptions;

public class OccupiedPositionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public OccupiedPositionException(Integer space) {
		super("The position [" + space + "] is already occupied");
	}
}
