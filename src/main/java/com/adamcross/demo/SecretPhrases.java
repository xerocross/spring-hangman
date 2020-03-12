package com.adamcross.demo;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SecretPhrases {
	private List<String> secretPhrases = new ArrayList<>();
}
