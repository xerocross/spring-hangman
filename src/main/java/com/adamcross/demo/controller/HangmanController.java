package com.adamcross.demo.controller;

import com.adamcross.demo.model.HangmanGame;
import com.adamcross.demo.service.HangmanResponse;
import com.adamcross.demo.service.PhraseService;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HangmanController {
	private final PhraseService phraseService;

	@GetMapping(path = "/hangman",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HangmanResponse> hangman() {
		List<String> phrases = phraseService.getSecretPhrases();
		Integer index = new Random().nextInt(phrases.size());
		HangmanGame hangmanGame = new HangmanGame((long) 0, phrases.get(index));
		return ResponseEntity.ok(new HangmanResponse(hangmanGame.viewSecretPhrase(), index));
	}

	@GetMapping(path = "/hangman/{phraseId}/{guessLetter}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HangmanResponse> hangmanGuess(@PathVariable Integer phraseId, @PathVariable String guessLetter) {
		List<String> phrases = phraseService.getSecretPhrases();
		HangmanGame hangmanGame = new HangmanGame((long) 0, phrases.get(phraseId));
		hangmanGame.guess(guessLetter);
		return ResponseEntity.ok(new HangmanResponse(hangmanGame.viewSecretPhrase(), phraseId));
	}

}
