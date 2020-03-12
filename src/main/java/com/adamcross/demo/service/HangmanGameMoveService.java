package com.adamcross.demo.service;

import com.adamcross.demo.HangmanGameMoveRepository;
import com.adamcross.demo.model.HangmanGameMove;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HangmanGameMoveService {

	private final HangmanGameMoveRepository hangmanGameMoveRepository;

	public List<HangmanGameMove> getMovesByGameId(Integer gameId) {
		List<HangmanGameMove> moves = new ArrayList<>();
		for (HangmanGameMove move : hangmanGameMoveRepository.findAll()) {
			if (move.getHangmanGameId() == gameId) {
				moves.add(move);
			}
		}
		return moves;
	}

	public Integer saveMove(HangmanGameMove hangmanGameMove) {
		hangmanGameMoveRepository.save(hangmanGameMove);
		return hangmanGameMove.getHangmanGameMoveId();
	}
}
