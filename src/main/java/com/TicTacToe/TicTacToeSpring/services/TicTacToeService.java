package com.TicTacToe.TicTacToeSpring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TicTacToe.TicTacToeSpring.DTOs.TicTacToeDTO;
import com.TicTacToe.TicTacToeSpring.entities.TicTacToe;
import com.TicTacToe.TicTacToeSpring.repositories.TicTacToeRepository;

@Service
public class TicTacToeService {
	
	@Autowired
	TicTacToeRepository repository;
	
	
	public List<TicTacToe> getAll() {
		return repository.findAll();
	}
	
	public TicTacToeDTO getById(Long id) {
		TicTacToe game = repository.findById(id).get();
		return convertToDTO(game);
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

	public void save(TicTacToe game) {
		repository.save(game);
	}

	public TicTacToe create() {
		if (repository.findAll().size() > 0) {
			TicTacToe ticTacToe = repository.findById(1L).get();
			System.out.println("Jogo ja criado");
			return ticTacToe; // aí vai salvar duplamente
		} else {
			TicTacToe ticTacToe = new TicTacToe();
			this.save(ticTacToe);
			return ticTacToe;
		}
		

	}
	
	public TicTacToe makeMove(Integer place) {
		
		if (place < 1 || place > 9) { // swap
			System.out.println("Erro aqui (espaço inexistente)");
			return null;
		}
		else {
			TicTacToe entityGame = repository.findById(1L).get();
			if (!entityGame.getIsRunning()) {
				System.out.println("Erro aqui (jogo já encerrado)");
				return null;
			}
			
			TicTacToeDTO game = convertToDTO(entityGame);
			
			
			
			ArrayList<Integer> occupedSpaces = getOccupedSpaces(game);
			if (!occupedSpaces.isEmpty()) {
				for (int i = 0; i < occupedSpaces.size(); i++) {
					if (place == occupedSpaces.get(i)) {
						System.out.println("Erro aqui (espaço já ocupado)");
						return null;
					}
				}
			}
			
			
			boolean isFinished = false;
			Integer moves = countMoves(game);
			
			if (moves % 2 == 0) { // par
				System.out.println("X jogou");
				game.setEntireGame(game.getEntireGame().replace(place.toString().charAt(0), 'X'));
				
				// ganhou?
				if (horizontalFinder('X', convertToEntity(game)) || verticalFinder('X', convertToEntity(game)) || diagonalFinder('X', convertToEntity(game)) ) {
					isFinished = true;
					System.out.println("X ganhou o jogo");
				}
			}
			else { // impar
				System.out.println("O jogou");
				game.setEntireGame(game.getEntireGame().replace(place.toString().charAt(0), 'O'));
				
				// ganhou?
				if (horizontalFinder('O', convertToEntity(game)) || verticalFinder('O', convertToEntity(game)) || diagonalFinder('O', convertToEntity(game)) ) {
					isFinished = true;
					System.out.println("O ganhou o jogo");
				}
			}
			
			return this.update(1L, game, isFinished);
			
			// salvar
		}
	}
	
	private ArrayList<Integer> getOccupedSpaces(TicTacToeDTO game) {
		ArrayList<Integer> occupedSpaces = new ArrayList<>();
		
		for (int i = 0; i<game.getEntireGame().length(); i++) {
			char space = game.getEntireGame().charAt(i);
			if (space == 'X' || space == 'O') {
				occupedSpaces.add(i+1); // o java conta do 0. Mas o meu jogo começa na casa 1.
			}
		}
		
		return occupedSpaces;
	}

	public void restart() {
		// não pode resetar sem ter criado nenhum jogo
		TicTacToe game = repository.getReferenceById(1L);
		
		game.setFirstLine("123");
		game.setSecondLine("456");
		game.setThirdLine("789");
		game.setIsRunning(true);
		repository.save(game);
		
		System.out.println("Jogo resetado, pode jogar dnv");
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

	// o X sempre começa primeiro
	private Integer countMoves(TicTacToeDTO game) {
		Integer moves = 0;
		for (int i = 0; i<game.getEntireGame().length(); i++) {
			if (game.getEntireGame().charAt(i) == 'X' || game.getEntireGame().charAt(i) == 'O') {
				moves += 1;
			}
		}
		return moves;
	}
	
	// há 8 maneiras distintas de ganhar
	// toda vez que uma jogada for feita, o codigo vai procurar por todos esses padrões
	private boolean diagonalFinder(char element, TicTacToe game) {
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
	
	private boolean horizontalFinder(char element, TicTacToe game) {
		
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
	
	private boolean verticalFinder(char element, TicTacToe game) {
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
}
