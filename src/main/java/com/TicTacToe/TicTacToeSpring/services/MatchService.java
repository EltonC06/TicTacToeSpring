package com.TicTacToe.TicTacToeSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TicTacToe.TicTacToeSpring.entities.Match;
import com.TicTacToe.TicTacToeSpring.entities.TicTacToe;
import com.TicTacToe.TicTacToeSpring.repositories.MatchRepository;
import com.TicTacToe.TicTacToeSpring.repositories.TicTacToeRepository;
import com.TicTacToe.TicTacToeSpring.services.exceptions.MatchNotCreatedException;

@Service
public class MatchService {
	
	@Autowired
	MatchRepository matchRepository;
	
	@Autowired
	TicTacToeRepository ticTacToeRepository;
	
	public Match getById(Long id) {
		if (!matchRepository.existsById(id)) {
			throw new MatchNotCreatedException();
		} else {
			return matchRepository.findById(id).get();
		}
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
		if (!matchRepository.existsById(id)) {
			throw new MatchNotCreatedException();
		} else {
			Match match = matchRepository.findById(id).get();
			if (ticTacToeRepository.existsById(match.getTicTacToe().getId())) {
				TicTacToe game = ticTacToeRepository.findById(id).get();
				game.setFirstLine("123");
				game.setSecondLine("456");
				game.setThirdLine("789");
				game.setIsRunning(true);
				game.setRoundWinner(null);
			}
			match.setDraws(0);
			match.setoVictories(0);
			match.setxVictories(0);
			match.setRoundsPlayed(0);
			match.setDraws(0);
			matchRepository.save(match);
			return match;
		}
	}
}
