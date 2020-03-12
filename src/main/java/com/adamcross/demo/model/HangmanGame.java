package com.adamcross.demo.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class HangmanGame {


	public HangmanGame(Long hangmanGameId, String secretPhraseString) {
		attachPhrase(secretPhraseString);
	}

	@Getter
	private Long hangmanGameId;
	private List<HangmanGameLetter> secretPhrase;

	private void attachPhrase(String secretPhraseString) {
		secretPhrase = new ArrayList<>();
		for (int i = 0; i < secretPhraseString.length(); i++) {
			char c = secretPhraseString.charAt(i);
			HangmanGameLetter hangmanGameLetter = new HangmanGameLetter(c, false);
			secretPhrase.add(hangmanGameLetter);
		}
	}

	public void guess(String letter) {
		if (letter.length() != 1) {
			throw new IllegalArgumentException();
		}
		String guessChar = letter.substring(0,1);
		for (HangmanGameLetter hangmanGameLetter : secretPhrase) {
			if (hangmanGameLetter.getSecretLetter().equalsIgnoreCase(guessChar)) {
				hangmanGameLetter.setIsRevealed(true);
			}
		}
	}

	public String viewSecretPhrase() {
		StringBuilder phraseBuilder = new StringBuilder();
		for (HangmanGameLetter hangmanGameLetter : secretPhrase) {
			if (hangmanGameLetter.getIsRevealed() || hangmanGameLetter.getSecretLetter().equals(" ")) {
				phraseBuilder.append(hangmanGameLetter.getSecretLetter());
			} else {
				phraseBuilder.append("_");
			}
		}
		return phraseBuilder.toString();
	}
}
