package com.TicTacToe.TicTacToeSpring.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_match")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer draws;
	private Integer xVictories;
	private Integer oVictories;
	private Integer roundsPlayed;
	@OneToOne(cascade = CascadeType.ALL)
	private TicTacToe ticTacToe;
	
	
	
	public Match() {
		this.draws = 0;
		this.xVictories = 0;
		this.oVictories = 0;
		this.roundsPlayed = 0;
	}

	public Match(Integer draws, Integer xVictories, Integer oVictories, Integer round) {
		super();
		this.draws = draws;
		this.xVictories = xVictories;
		this.oVictories = oVictories;
		this.roundsPlayed = round;
	}
	
	public Long getId() {
		return id;
	}

	public Integer getDraws() {
		return draws;
	}

	public void setDraws(Integer draws) {
		this.draws = draws;
	}

	public Integer getxVictories() {
		return xVictories;
	}

	public void setxVictories(Integer xVictories) {
		this.xVictories = xVictories;
	}

	public Integer getoVictories() {
		return oVictories;
	}

	public void setoVictories(Integer oVictories) {
		this.oVictories = oVictories;
	}

	public Integer getRoundsPlayed() {
		return roundsPlayed;
	}

	public void setRoundsPlayed(Integer round) {
		this.roundsPlayed = round;
	}

	public TicTacToe getTicTacToe() {
		return ticTacToe;
	}

	public void setTicTacToe(TicTacToe ticTacToe) {
		this.ticTacToe = ticTacToe;
	}
}
