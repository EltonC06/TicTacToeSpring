package com.TicTacToe.TicTacToeSpring.entities;

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
	private Integer round;
	@OneToOne
	private TicTacToe ticTacToe;
	
	public Match() {
		
	}

	public Match(Integer draws, Integer xVictories, Integer oVictories, Integer round) {
		super();
		this.draws = draws;
		this.xVictories = xVictories;
		this.oVictories = oVictories;
		this.round = round;
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

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}
}
