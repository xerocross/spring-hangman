package com.adamcross.demo;

import com.adamcross.demo.model.HangmanGameType;
import org.springframework.data.repository.CrudRepository;

public interface HangmanGameRepository extends CrudRepository<HangmanGameType, Integer> {
}
