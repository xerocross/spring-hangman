package com.adamcross.demo;

import com.adamcross.demo.model.HangmanGameMove;
import org.springframework.data.repository.CrudRepository;

public interface HangmanGameMoveRepository extends CrudRepository<HangmanGameMove, Integer> {

}
