package com.TicTacToe.TicTacToeSpring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tictactoe")
public class TicTacToe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstLine;
	private String secondLine;
	private String thirdLine;
	private Boolean isRunning;
	

	// id gerado automaticamente
	public TicTacToe() {
		super();
		this.firstLine = "123";
		this.secondLine = "456";
		this.thirdLine = "789";
		this.isRunning = false;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}

	public String getSecondLine() {
		return secondLine;
	}

	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}

	public String getThirdLine() {
		return thirdLine;
	}

	public void setThirdLine(String thirdLine) {
		this.thirdLine = thirdLine;
	}
	
	public Boolean getIsRunning() {
		return isRunning;
	}
	public void setIsRunning(Boolean isRunning) {
		this.isRunning = isRunning;
	}
	public void startGame() {
		if (isRunning) {
			resetGame();
		}
		
		this.setIsRunning(true);
		
		
	}

	private void resetGame() {
		this.setFirstLine("###");
		this.setSecondLine("###");
		this.setThirdLine("###");
		this.setIsRunning(false);
	}


	
	
	
	
}
