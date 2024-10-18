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
	
	@GetMapping("/{matchId}")
	public ResponseEntity<TicTacToe> getById(@PathVariable(name = "matchId") Long matchId) {
		TicTacToe ticTacToe = service.getById(matchId);
		return ResponseEntity.ok().body(ticTacToe);
	}

	@PutMapping("/play/{matchId}/{position}")
	public TicTacToe makeMove(@PathVariable(name = "matchId") Long matchId, @PathVariable(name = "position") Integer position) {
		return service.makeMove(matchId, position);
	}
	
	@PutMapping("/restart/{matchId}")
	public ResponseEntity<TicTacToe> restart(@PathVariable(name = "matchId") Long matchId) {
		TicTacToe ticTacToe = service.restart(matchId);
		return ResponseEntity.ok().body(ticTacToe);
	}
}
