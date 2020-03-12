package com.adamcross.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
public class HangmanGameType {
	@Id
	@GeneratedValue
	@Getter
	@Setter
	private Integer hangmanGameId;

	public HangmanGameType() {
	}

	public HangmanGameType(Integer phraseId, Integer sessionId) {
		this.phraseId = phraseId;
		this.sessionId = sessionId;
	}

	@Getter
	@Setter
	private Integer phraseId;

	@Getter
	@Setter
	private Integer sessionId;
}
