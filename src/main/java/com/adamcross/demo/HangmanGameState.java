package com.adamcross.demo;

import com.adamcross.demo.model.HangmanGameLetter;
import com.adamcross.demo.model.HangmanGameMove;
import com.adamcross.demo.model.HangmanGameType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

public class HangmanGameState {

	public HangmanGameState(Integer hangmanGameId, String secretPhraseString) {
		attachPhrase(secretPhraseString);
	}

	@Getter
	private Integer hangmanGameId;
	private List<HangmanGameLetter> secretPhrase;
	@Getter
	@Setter
	private Integer phraseId;

	@Getter
	@Setter
	private List<HangmanGameMove> moves;

	@Getter
	private Set<String> revealedLetters = new HashSet<>();



	private void attachPhrase(String secretPhraseString) {
		secretPhrase = new ArrayList<>();
		for (int i = 0; i < secretPhraseString.length(); i++) {
			char c = secretPhraseString.charAt(i);
			HangmanGameLetter hangmanGameLetter = new HangmanGameLetter(c, false);
			secretPhrase.add(hangmanGameLetter);
		}
	}

	private void guess(String letter) {
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

	public void move(HangmanGameMove move) {
		revealedLetters.add(move.getGuessLetter());
		this.guess(move.getGuessLetter());
	}

//
//	public String viewSecretPhrase() {
//		Set<String> revealedLetters = getRevealedLetters();
//
//		StringBuilder phraseBuilder = new StringBuilder();
//		for (HangmanGameLetter hangmanGameLetter : secretPhrase) {
//			if ( || hangmanGameLetter.getSecretLetter().equals(" ")) {
//				phraseBuilder.append(hangmanGameLetter.getSecretLetter());
//			} else {
//				phraseBuilder.append("_");
//			}
//		}
//		return phraseBuilder.toString();
//	}

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
