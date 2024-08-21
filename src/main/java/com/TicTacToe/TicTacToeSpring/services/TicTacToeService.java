package com.TicTacToe.TicTacToeSpring.services;

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
		if (place < 1 || place > 9) {
			System.out.println("Erro aqui");
			return null;
		}
		else {
			TicTacToeDTO game = convertToDTO(repository.findById(1L).get());
			Integer moves = countMoves(game);
			
			System.out.println("Movimentos: " + moves);
			
			// adicionar tratamento de exceção quando selecionar casa ja selecionada
			// lista de casas já jogadas
			
			if (moves % 2 == 0) { // par
				System.out.println("X jogou");
				game.setEntireGame(game.getEntireGame().replace(place.toString().charAt(0), 'X'));
				System.out.println(game.getEntireGame());
			}
			else { // impar
				System.out.println("O jogou");
				game.setEntireGame(game.getEntireGame().replace(place.toString().charAt(0), 'O'));
				System.out.println(game.getEntireGame());
			}
			
			return this.update(1L, game);
			
			// salvar
		}
	}
	
	public void restart() {
		TicTacToe game = repository.getReferenceById(1L);
		
		game.setFirstLine("123");
		game.setSecondLine("456");
		game.setThirdLine("789");
		game.setIsRunning(false);
		
		repository.save(game);
		
	}
	
	
	
	private TicTacToe update(Long id, TicTacToeDTO game) {
		
		TicTacToe gameToUpdate = repository.findById(id).get();
		
		gameToUpdate = updateData(gameToUpdate, game);
		
		return this.repository.save(gameToUpdate);
	}

	private TicTacToe updateData(TicTacToe oldGame, TicTacToeDTO game) {
		
		oldGame.setFirstLine(game.getEntireGame().substring(0, 3));
		oldGame.setSecondLine(game.getEntireGame().substring(3, 6));
		oldGame.setThirdLine(game.getEntireGame().substring(6, 9));
		oldGame.setIsRunning(true);
		
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
	/*
	// há 8 maneiras distintas de ganhar]
	// toda vez que uma jogada for feita, o codigo vai procurar por todos esses padrões
	private boolean diagonalFinder(char element, TicTacToe game) {
		// diagonal esquerda pra direita
		
		
		
		// diagonal direita para esquerda
		
		
		
	}
	
	private boolean horizontalFinder(char element, TicTacToe game) {
		// primeira coluna
		
		
		// segunda coluna
		
		
		// terceira coluna
		
		
		
	}
	
	private boolean verticalFinder(char element, TicTacToe game) {
		// primeira linha
		
		
		// segunda linha
		
		
		// terceira linha
		
		
	}
	*/
}
