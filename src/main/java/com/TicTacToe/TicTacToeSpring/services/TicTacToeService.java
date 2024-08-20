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
		TicTacToe ticTacToe = new TicTacToe();
		return ticTacToe;
	}
	
	public TicTacToe playTurn(Long id, Integer place) {
		if (place < 1 || place > 9) {
			System.out.println("Erro aqui");
		}
		else {
			TicTacToeDTO game = convertToDTO(repository.findById(id).get());
			Integer moves = countMoves(game);
			
			if (moves % 2 == 0) { // par
				game.getEntireGame().replace(place.toString().charAt(0), 'X');
				
			}
			else { // impar
				game.getEntireGame().replace(place.toString().charAt(0), 'O');
				
			}
			// salvar
		}
		return null;

	}
	// o X sempre começa primeiro
	private Integer countMoves(TicTacToeDTO game) {
		Integer moves = 0;
		for (int i = 0; i<game.getEntireGame().length(); i++) {
			System.out.println(game.getEntireGame().charAt(i));
			if (game.getEntireGame().charAt(i) == 'X') {
				moves += 1;
			}
		}
		return moves;
	}	
}
