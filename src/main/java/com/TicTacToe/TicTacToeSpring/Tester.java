package com.TicTacToe.TicTacToeSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.TicTacToe.TicTacToeSpring.entities.TicTacToe;
import com.TicTacToe.TicTacToeSpring.repositories.TicTacToeRepository;
import com.TicTacToe.TicTacToeSpring.services.TicTacToeService;

@SpringBootApplication
public class Tester implements CommandLineRunner {
	@Autowired
	TicTacToeRepository repository;
	
	@Autowired
	TicTacToeService service;
	
	@Override
	public void run(String... args) throws Exception {
		/*
		TicTacToe game = new TicTacToe("###", "###", "###");
		
		service.save(game);
		*/
	}

	
}
