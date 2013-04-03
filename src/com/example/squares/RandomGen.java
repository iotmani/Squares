package com.example.squares;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RandomGen {

    private static final Random r = new Random();

	public static int generateFromRange(int min, int max) {
		 if (min > max) {
		      int aux = min;
		      min = max;
		      max = aux;
		 }
		  int i = max - min;
		  int x = r.nextInt(i + 1);
		 return x + min;
	 }

    private static String generateFromStringArray(List<String> input) {
		int i = generateFromRange(0, input.size() - 1);
		return input.get(i);
	}


	public static String generateFromStringCollection(List<String> input) {
		return generateFromStringArray(input);
	}
}
