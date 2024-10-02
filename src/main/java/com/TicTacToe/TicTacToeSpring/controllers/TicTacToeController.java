package com.TicTacToe.TicTacToeSpring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TicTacToe.TicTacToeSpring.DTOs.TicTacToeDTO;
import com.TicTacToe.TicTacToeSpring.entities.TicTacToe;
import com.TicTacToe.TicTacToeSpring.services.TicTacToeService;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*")
public class TicTacToeController {
	
	@Autowired
	TicTacToeService service;
	
	@GetMapping
	public List<TicTacToe> getGames() {
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TicTacToeDTO> getById(@PathVariable(name = "id") Long id) {
		TicTacToeDTO ticTacToe = service.getById(id);
		return ResponseEntity.ok().body(ticTacToe);
	}

	@PutMapping("/play/{position}")
	public TicTacToe makeMove(@PathVariable(name = "position") Integer position) {
		return service.makeMove(position);
	}
	
	@PutMapping("/restart")
	public void restart() {
		service.restart();
	}
}
