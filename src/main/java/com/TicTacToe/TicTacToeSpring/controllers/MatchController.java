package com.TicTacToe.TicTacToeSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TicTacToe.TicTacToeSpring.entities.Match;
import com.TicTacToe.TicTacToeSpring.services.MatchService;

@RestController
@RequestMapping("/match")
@CrossOrigin(origins = "*")
public class MatchController {
	
	@Autowired
	MatchService matchService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Match> getById(@PathVariable(value = "id") Long id) {
		Match match = matchService.getById(id);
		return ResponseEntity.ok().body(match);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Match> create() {
		Match match = matchService.create();
		return ResponseEntity.ok().body(match);
	}
	
	@PutMapping("/reset/{id}")
	public ResponseEntity<Match> reset(@PathVariable Long id) {
		Match match = matchService.reset(id);
		return ResponseEntity.ok().body(match);
	}
}
