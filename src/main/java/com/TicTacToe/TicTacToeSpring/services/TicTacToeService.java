package com.TicTacToe.TicTacToeSpring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TicTacToe.TicTacToeSpring.DTOs.TicTacToeDTO;
import com.TicTacToe.TicTacToeSpring.entities.TicTacToe;
import com.TicTacToe.TicTacToeSpring.repositories.TicTacToeRepository;
import com.TicTacToe.TicTacToeSpring.services.exceptions.GameAlreadyCreated;
import com.TicTacToe.TicTacToeSpring.services.exceptions.GameNotCreatedException;
import com.TicTacToe.TicTacToeSpring.services.exceptions.GameNotRunningException;
import com.TicTacToe.TicTacToeSpring.services.exceptions.OccupiedPositionException;
import com.TicTacToe.TicTacToeSpring.services.exceptions.PositionNotFoundException;

@Service
public class TicTacToeService {
	
	@Autowired
	TicTacToeRepository repository;
	
	public List<TicTacToe> getAll() {
		return repository.findAll();
	}
	
	public TicTacToeDTO getById(Long id) {
		if (repository.existsById(id)) {
			TicTacToe game = repository.findById(id).get();
			return convertToDTO(game);
		} else {
			throw new GameNotCreatedException();
		}
	}

	public TicTacToe create() {
		if (repository.existsById(1L)) {
			throw new GameAlreadyCreated();
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
			
			repository.save(game);
		} else {
			throw new GameNotCreatedException();
		}
	}
	
	public TicTacToe makeMove(Integer place) {
		if (!repository.existsById(1L)) {
			throw new GameNotCreatedException();
		}
		
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
		
		if (moves == 8) { // se for = 8, significa que essa nova jogada já será a 9ª
			isFinished = true;
		}
		
		if (moves % 2 == 0) {
			game.setEntireGame(game.getEntireGame().replace(place.toString().charAt(0), 'X'));
			if (victoryCheck('X', game)) {
				isFinished = true;
			}
		}
		else {
			game.setEntireGame(game.getEntireGame().replace(place.toString().charAt(0), 'O'));
			if (victoryCheck('O', game)) {
				isFinished = true;
			}
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

	private TicTacToe updateData(TicTacToe oldGame, TicTacToeDTO game, boolean isFinished) {
		oldGame.setFirstLine(game.getEntireGame().substring(0, 3));
		oldGame.setSecondLine(game.getEntireGame().substring(3, 6));
		oldGame.setThirdLine(game.getEntireGame().substring(6, 9));
		oldGame.setIsRunning(!isFinished);
		
		return oldGame;
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
		// diagonal esquerda pra direita
		if (game.getFirstLine().charAt(0) == element) {
			if (game.getSecondLine().charAt(1) == element) {
				if (game.getThirdLine().charAt(2) == element) {
					return true;
				}
			}
		}
		// diagonal direita para esquerda
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
		// primeira linha
		if (game.getFirstLine().charAt(0) == element) {
			if (game.getFirstLine().charAt(1) == element) {
				if (game.getFirstLine().charAt(2) == element) {
					return true;
				}
			}
		}
		// segunda linha
		if (game.getSecondLine().charAt(0) == element) {
			if (game.getSecondLine().charAt(1) == element) {
				if (game.getSecondLine().charAt(2) == element) {
					return true;
				}
			}
		}
		// terceira linha
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
		// primeira, segunda e terceira coluna
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
		
		return convertedGame;
	}
	
	private TicTacToe convertToEntity(TicTacToeDTO game) {
		TicTacToe convertedGame = new TicTacToe();
		
		convertedGame.setFirstLine(game.getEntireGame().substring(0, 3));
		convertedGame.setSecondLine(game.getEntireGame().substring(3, 6));
		convertedGame.setThirdLine(game.getEntireGame().substring(6, 9));
		convertedGame.setIsRunning(true);
		
		return convertedGame;
	}
}
