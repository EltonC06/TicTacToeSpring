package com.TicTacToe.TicTacToeSpring.services.exceptions;

public class MatchNotCreatedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MatchNotCreatedException() {
		super("Match not created yet");
	}
}
