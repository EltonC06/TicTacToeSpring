package com.TicTacToe.TicTacToeSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TicTacToe.TicTacToeSpring.entities.Match;
import com.TicTacToe.TicTacToeSpring.entities.TicTacToe;
import com.TicTacToe.TicTacToeSpring.repositories.MatchRepository;

@Service
public class MatchService {
	
	@Autowired
	MatchRepository matchRepository;
	
	public Match getById(Long id) {
		return matchRepository.findById(id).get();
	}
	
	public Match create() {
		TicTacToe ticTacToe = new TicTacToe();
		Match match = new Match();
		match.setTicTacToe(ticTacToe);
		matchRepository.save(match);
		return match;
	}
	
	public Match updade(Long id, Match match) {
		Match matchToUpdate = matchRepository.findById(id).get();
		matchToUpdate.setDraws(match.getDraws());
		matchToUpdate.setoVictories(match.getoVictories());
		matchToUpdate.setxVictories(match.getxVictories());
		matchToUpdate.setRoundsPlayed(match.getRoundsPlayed());
		
		return matchRepository.save(matchToUpdate);
	}
	
	public Match reset(Long id) {
		Match match = matchRepository.findById(id).get();
		match.setDraws(0);
		match.setoVictories(0);
		match.setxVictories(0);
		match.setRoundsPlayed(0);
		match.setDraws(0);
		matchRepository.save(match);
		return match;
	}
}
