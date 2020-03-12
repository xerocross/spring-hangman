package com.adamcross.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class HangmanResponse {

	private String secretPhrase;
	private Integer phraseId;
	private Integer gameId;
	private List<String> revealedLetters;

	public HangmanResponse(String secretPhrase, Integer phraseId, Integer gameId, Set<String> revealedLetters) {
		this.secretPhrase = secretPhrase;
		this.phraseId = phraseId;
		this.gameId = gameId;
		this.revealedLetters = new ArrayList<>(revealedLetters);
	}
}
