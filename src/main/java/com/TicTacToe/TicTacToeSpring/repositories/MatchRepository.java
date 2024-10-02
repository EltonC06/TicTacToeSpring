package com.TicTacToe.TicTacToeSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.TicTacToe.TicTacToeSpring.entities.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
