package com.TicTacToe.TicTacToeSpring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TicTacToe.TicTacToeSpring.DTOs.TicTacToeDTO;
import com.TicTacToe.TicTacToeSpring.entities.TicTacToe;
import com.TicTacToe.TicTacToeSpring.services.TicTacToeService;

@RestController
@RequestMapping("/games")
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
	
	/*
	@PostMapping
	public void saveGame(@RequestBody TicTacToe ticTacToe) {
		service.save(ticTacToe);
	}
	*/
	
	@PostMapping("/play")
	public ResponseEntity<TicTacToe> create() {
		TicTacToe game = service.create();
		return ResponseEntity.ok().body(game);
	}
	
	@PutMapping("/play/{space}")
	public TicTacToe makeMove(@PathVariable(name = "space") Integer space) {
		return service.makeMove(space);
	
	}
	
	@PutMapping("/restart")
	public void restart() {
		service.restart();
	}

}
