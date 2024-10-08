package com.TicTacToe.TicTacToeSpring.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TicTacToe.TicTacToeSpring.DTOs.TicTacToeDTO;
import com.TicTacToe.TicTacToeSpring.entities.Match;
import com.TicTacToe.TicTacToeSpring.entities.TicTacToe;
import com.TicTacToe.TicTacToeSpring.repositories.TicTacToeRepository;
import com.TicTacToe.TicTacToeSpring.services.exceptions.GameAlreadyCreatedException;
import com.TicTacToe.TicTacToeSpring.services.exceptions.GameNotCreatedException;
import com.TicTacToe.TicTacToeSpring.services.exceptions.GameNotRunningException;
import com.TicTacToe.TicTacToeSpring.services.exceptions.OccupiedPositionException;
import com.TicTacToe.TicTacToeSpring.services.exceptions.PositionNotFoundException;

@Service
public class TicTacToeService {
	
	@Autowired
	TicTacToeRepository repository;
	
	@Autowired
	MatchService matchService;
	
	public TicTacToe getById(Long id) {
		if (repository.existsById(id)) {
			TicTacToe game = repository.findById(id).get();
			return game;
		} else {
			throw new GameNotCreatedException();
		}
	}

	public TicTacToe create() {
		if (repository.existsById(1L)) {
			throw new GameAlreadyCreatedException();
		} else {
			TicTacToe ticTacToe = new TicTacToe();
			this.save(ticTacToe);
			return ticTacToe;
		}
	}
	
	public void restart() {
		if (repository.existsById(1L)) {
			TicTacToe game = repository.getReferenceById(1L);
			
			game.setFirstLine("123");
			game.setSecondLine("456");
			game.setThirdLine("789");
			game.setIsRunning(true);
			game.setRoundWinner(null);
			
			repository.save(game);
		} else {
			throw new GameNotCreatedException();
		}
	}
	
	public TicTacToe makeMove(Integer place) {
		if (!repository.existsById(1L)) {
			throw new GameNotCreatedException();
		}
		
		Match match = matchService.getById(1L);
		
		TicTacToe entityGame = repository.findById(1L).get();
		
		if (!entityGame.getIsRunning()) {
			throw new GameNotRunningException();
		}
		
		if (place < 1 || place > 9) {
			throw new PositionNotFoundException(place);
		}
				
		TicTacToeDTO game = convertToDTO(entityGame);
			
		ArrayList<Integer> occupedSpaces = getOccupiedSpaces(game);
		
		if (!occupedSpaces.isEmpty()) {
			for (Integer i = 0; i < occupedSpaces.size(); i++) {
				if (place == occupedSpaces.get(i)) {
					throw new OccupiedPositionException(place);
				}
			}
		}
		
		boolean isFinished = false;

		Integer moves = countMoves(game);
		
		if (moves == 8) {
			isFinished = true;
		}
		
		if (moves % 2 == 0) {
			game.setEntireGame(game.getEntireGame().replace(place.toString().charAt(0), 'X'));
			if (victoryCheck('X', game)) {
				isFinished = true;
				game.setRoundWinner("X");
				match.setxVictories(match.getxVictories()+1);
				match.setRoundsPlayed(match.getRoundsPlayed()+1);
			}
		}
		else {
			game.setEntireGame(game.getEntireGame().replace(place.toString().charAt(0), 'O'));
			if (victoryCheck('O', game)) {
				isFinished = true;
				game.setRoundWinner("O");
				match.setoVictories(match.getoVictories()+1);
				match.setRoundsPlayed(match.getRoundsPlayed()+1);
			}
		}
		
		if (isFinished && !victoryCheck('X', game) && !victoryCheck('O', game)) {
			game.setRoundWinner("D");
			match.setDraws(match.getDraws()+1);
			match.setRoundsPlayed(match.getRoundsPlayed()+1);
		}

		return this.update(1L, game, isFinished);
	}
	
	private ArrayList<Integer> getOccupiedSpaces(TicTacToeDTO game) {
		ArrayList<Integer> occupedSpaces = new ArrayList<>();
		
		for (int i = 0; i<game.getEntireGame().length(); i++) {
			char space = game.getEntireGame().charAt(i);
			if (space == 'X' || space == 'O') {
				occupedSpaces.add(i+1);
			}
		}
		return occupedSpaces;
	}
	
	private Integer countMoves(TicTacToeDTO game) {
		Integer moves = 0;
		for (int i = 0; i<game.getEntireGame().length(); i++) {
			if (game.getEntireGame().charAt(i) == 'X' || game.getEntireGame().charAt(i) == 'O') {
				moves += 1;
			}
		}
		return moves;
	}

	private void save(TicTacToe game) {
		repository.save(game);
	}

	private TicTacToe update(Long id, TicTacToeDTO game, boolean isFinished) {
		TicTacToe gameToUpdate = repository.findById(id).get();
		
		gameToUpdate = updateData(gameToUpdate, game, isFinished);
		
		return this.repository.save(gameToUpdate);
	}

	private TicTacToe updateData(TicTacToe gameToUpdate, TicTacToeDTO game, boolean isFinished) {
		gameToUpdate.setFirstLine(game.getEntireGame().substring(0, 3));
		gameToUpdate.setSecondLine(game.getEntireGame().substring(3, 6));
		gameToUpdate.setThirdLine(game.getEntireGame().substring(6, 9));
		gameToUpdate.setIsRunning(!isFinished);
		gameToUpdate.setRoundWinner(game.getRoundWinner());
		
		return gameToUpdate;
	}
	
	private boolean victoryCheck(char symbol, TicTacToeDTO gameDTO) {
		TicTacToe game = convertToEntity(gameDTO);
		
		if (horizontalVictoryFinder(symbol, game) || verticalVictoryFinder(symbol, game) || diagonalVictoryFinder(symbol, game)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean diagonalVictoryFinder(char element, TicTacToe game) {
		if (game.getFirstLine().charAt(0) == element) {
			if (game.getSecondLine().charAt(1) == element) {
				if (game.getThirdLine().charAt(2) == element) {
					return true;
				}
			}
		}
		
		if (game.getFirstLine().charAt(2) == element) {
			if (game.getSecondLine().charAt(1) == element) {
				if (game.getThirdLine().charAt(0) == element) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean horizontalVictoryFinder(char element, TicTacToe game) {
		if (game.getFirstLine().charAt(0) == element) {
			if (game.getFirstLine().charAt(1) == element) {
				if (game.getFirstLine().charAt(2) == element) {
					return true;
				}
			}
		}
		
		if (game.getSecondLine().charAt(0) == element) {
			if (game.getSecondLine().charAt(1) == element) {
				if (game.getSecondLine().charAt(2) == element) {
					return true;
				}
			}
		}
		
		if (game.getThirdLine().charAt(0) == element) {
			if (game.getThirdLine().charAt(1) == element) {
				if (game.getThirdLine().charAt(2) == element) {
					return true;
				} 
			}
		}
		return false;
	}
	
	private boolean verticalVictoryFinder(char element, TicTacToe game) {
		for (int i = 0; i<3; i++) {
			if (game.getFirstLine().charAt(i) == element) {
				if (game.getSecondLine().charAt(i) == element) {
					if (game.getThirdLine().charAt(i) == element) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private TicTacToeDTO convertToDTO(TicTacToe game) {
		TicTacToeDTO convertedGame = new TicTacToeDTO();
		
		convertedGame.setEntireGame(game.getFirstLine() + "" + game.getSecondLine() + "" + game.getThirdLine());
		convertedGame.setRoundWinner(game.getRoundWinner());
		
		return convertedGame;
	}
	
	private TicTacToe convertToEntity(TicTacToeDTO game) {
		TicTacToe convertedGame = new TicTacToe();
		
		convertedGame.setFirstLine(game.getEntireGame().substring(0, 3));
		convertedGame.setSecondLine(game.getEntireGame().substring(3, 6));
		convertedGame.setThirdLine(game.getEntireGame().substring(6, 9));
		convertedGame.setIsRunning(true);
		convertedGame.setRoundWinner(game.getRoundWinner());
		
		return convertedGame;
	}
}
