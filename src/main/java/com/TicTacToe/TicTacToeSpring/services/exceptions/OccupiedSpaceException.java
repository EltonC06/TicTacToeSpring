package com.TicTacToe.TicTacToeSpring.services.exceptions;

public class OccupiedSpaceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public OccupiedSpaceException() {
		super("Space already occupied");
	}
	

}
