package com.TicTacToe.TicTacToeSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TicTacToe.TicTacToeSpring.entities.TicTacToe;
import com.TicTacToe.TicTacToeSpring.services.TicTacToeService;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*")
public class TicTacToeController {
	
	@Autowired
	TicTacToeService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<TicTacToe> getById(@PathVariable(name = "id") Long id) {
		TicTacToe ticTacToe = service.getById(id);
		return ResponseEntity.ok().body(ticTacToe);
	}

	@PutMapping("/play/{position}")
	public TicTacToe makeMove(@PathVariable(name = "position") Integer position) {
		return service.makeMove(position);
	}
	
	@PutMapping("/restart")
	public ResponseEntity<TicTacToe> restart() {
		TicTacToe ticTacToe = service.restart();
		return ResponseEntity.ok().body(ticTacToe);
	}
}
