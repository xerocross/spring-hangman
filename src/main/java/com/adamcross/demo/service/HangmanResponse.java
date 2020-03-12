package com.adamcross.demo.service;

import lombok.Data;

@Data
public class HangmanResponse {

	private String secretPhrase;
	private Integer phraseId;

	public HangmanResponse(String secretPhrase, Integer phraseId) {
		this.secretPhrase = secretPhrase;
		this.phraseId = phraseId;
	}

}
