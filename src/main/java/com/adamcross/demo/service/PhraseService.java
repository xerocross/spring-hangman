package com.adamcross.demo.service;

import com.adamcross.demo.SecretPhrases;
import java.util.List;
import java.util.Random;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhraseService {
	private final SecretPhrases secretPhrases;

	public List<String> getSecretPhrases() {
		return secretPhrases.getSecretPhrases();
	}

	public String getRandom(){
		return secretPhrases.getSecretPhrases().get(new Random().nextInt(secretPhrases.getSecretPhrases().size()));
	}
}
