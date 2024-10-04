package com.TicTacToe.TicTacToeSpring.services.exceptions;

public class MatchAlreadyCreatedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MatchAlreadyCreatedException() {
		super("The match is already created");
	}
}
