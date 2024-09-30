package com.TicTacToe.TicTacToeSpring.DTOs;

public class TicTacToeDTO {
	
	private String entireGame;
	private String roundWinner;
	
	public TicTacToeDTO() {
		
	}

	public TicTacToeDTO(String entireGame) {
		super();
		this.entireGame = entireGame;
	}

	public String getEntireGame() {
		return entireGame;
	}

	public void setEntireGame(String entireGame) {
		this.entireGame = entireGame;
	}

	public String getRoundWinner() {
		return roundWinner;
	}

	public void setRoundWinner(String roundWinner) {
		this.roundWinner = roundWinner;
	}
}
