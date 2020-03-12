package com.adamcross.demo;

import com.adamcross.demo.model.HangmanGameMove;
import com.adamcross.demo.model.HangmanGameType;
import com.adamcross.demo.service.HangmanGameMoveService;
import com.adamcross.demo.service.PhraseService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HangmanGameService {

	private final HangmanGameRepository hangmanGameRepository;
	private final HangmanGameMoveService hangmanGameMoveService;
	private final PhraseService phraseService;


	public List<HangmanGameType> getAllHangmanGames() {
		List<HangmanGameType> games = new ArrayList<>();
		hangmanGameRepository.findAll().forEach(games::add);
		return games;
	}

	public HangmanGameType getGameById(int id) {
		return hangmanGameRepository.findById(id).get();
	}

	public Integer save(HangmanGameType hangmanGameType) {
		hangmanGameRepository.save(hangmanGameType);
		return hangmanGameType.getHangmanGameId();
	}

	public HangmanGameState getHangmanGameState(Integer gameId) {
		HangmanGameType hangmanGameType = hangmanGameRepository.findById(gameId).orElse(null);
		List<HangmanGameMove> moves = hangmanGameMoveService.getMovesByGameId(gameId);
		String phrase = phraseService.getSecretPhrases().get(hangmanGameType.getPhraseId());
		HangmanGameState hangmanGameState = new HangmanGameState(hangmanGameType.getHangmanGameId(), phrase);
		hangmanGameState.setPhraseId(hangmanGameType.getPhraseId());
		for (HangmanGameMove move : moves) {
			hangmanGameState.move(move);
		}
		return hangmanGameState;
	}
}
