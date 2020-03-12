package com.adamcross.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class HangmanGameMove {
	@Id
	@GeneratedValue
	@Getter
	@Setter
	private int hangmanGameMoveId;

	@Getter
	@Setter
	private int hangmanGameId;

	@Getter
	@Setter
	private String guessLetter;

	@Getter
	@Setter
	private int guessIndex;

}
