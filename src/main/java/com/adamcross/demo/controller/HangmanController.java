package com.adamcross.demo.controller;

import com.adamcross.demo.HangmanGameService;
import com.adamcross.demo.HangmanGameState;
import com.adamcross.demo.model.HangmanGameMove;
import com.adamcross.demo.model.HangmanGameType;
import com.adamcross.demo.service.HangmanGameMoveService;
import com.adamcross.demo.service.HangmanResponse;
import com.adamcross.demo.service.PhraseService;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HangmanController {
	private final PhraseService phraseService;
	private final HangmanGameService hangmanGameService;
	private final HangmanGameMoveService hangmanGameMoveService;

	@PostMapping(path = "/newGame",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HangmanResponse> hangman() {
		List<String> phrases = phraseService.getSecretPhrases();
		Integer index = new Random().nextInt(phrases.size());
		HangmanGameType hangmanGameType = new HangmanGameType(index, 0);
		HangmanGameState hangmanGameState = new HangmanGameState(0, phrases.get(index));
		Integer gameId = hangmanGameService.save(hangmanGameType);
		return ResponseEntity.ok(new HangmanResponse(hangmanGameState.viewSecretPhrase(), index, gameId, hangmanGameState.getRevealedLetters()));
	}

	@GetMapping(path = "/{gameId}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HangmanResponse> getGameState(@PathVariable Integer gameId) {
		HangmanGameState hangmanGameState =  hangmanGameService.getHangmanGameState(gameId);
		return ResponseEntity.ok(new HangmanResponse(hangmanGameState.viewSecretPhrase(), hangmanGameState.getPhraseId(), gameId, hangmanGameState.getRevealedLetters()));
	}

	@PostMapping(path = "/{gameId}/{guessLetter}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HangmanResponse> hangman(@PathVariable Integer gameId, @PathVariable String guessLetter) {
		HangmanGameType hangmanGameType =  hangmanGameService.getGameById(gameId);
		HangmanGameState hangmanGameState =  hangmanGameService.getHangmanGameState(gameId);
		HangmanGameMove hangmanGameMove = new HangmanGameMove();
		hangmanGameMove.setHangmanGameId(gameId);
		hangmanGameMove.setGuessLetter(guessLetter);
		hangmanGameMoveService.saveMove(hangmanGameMove);
		hangmanGameState =  hangmanGameService.getHangmanGameState(gameId);
		return ResponseEntity.ok(new HangmanResponse(hangmanGameState.viewSecretPhrase(), hangmanGameType.getPhraseId(), gameId, hangmanGameState.getRevealedLetters()));
	}


//	@PostMapping(path = "/hangman",  produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<HangmanResponse> hangman() {
//		List<String> phrases = phraseService.getSecretPhrases();
//		Integer index = new Random().nextInt(phrases.size());
//		HangmanGame hangmanGame = new HangmanGame((long) 0, phrases.get(index));
//
//		return ResponseEntity.ok(new HangmanResponse(hangmanGame.viewSecretPhrase(), index));
//	}
//

//
//	@GetMapping(path = "/hangman/{phraseId}/{guessLetter}",  produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<HangmanResponse> hangmanGuess(@PathVariable Integer phraseId, @PathVariable String guessLetter) {
//		List<String> phrases = phraseService.getSecretPhrases();
//		HangmanGame hangmanGame = new HangmanGame((long) 0, phrases.get(phraseId));
//		hangmanGame.guess(guessLetter);
//		return ResponseEntity.ok(new HangmanResponse(hangmanGame.viewSecretPhrase(), phraseId));
//	}

}
