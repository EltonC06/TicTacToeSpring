package com.TicTacToe.TicTacToeSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TicTacToe.TicTacToeSpring.entities.TicTacToe;

@Repository
public interface TicTacToeRepository extends JpaRepository<TicTacToe, Long> {

}
