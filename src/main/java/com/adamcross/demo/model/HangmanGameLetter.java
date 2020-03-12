package com.adamcross.demo.model;

import lombok.Getter;
import lombok.Setter;

public class HangmanGameLetter {

	@Getter
	@Setter
	private String secretLetter;

	@Getter
	@Setter
	private Boolean isRevealed;

	public HangmanGameLetter (char character, boolean isRevealed) {
		this.secretLetter = Character.toString(character);
		this.isRevealed = isRevealed;
	}
}
