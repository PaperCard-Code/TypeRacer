package util;

import dao.wordcategoryDAO;
import dao.wordpoolDAO;

import model.WordCategory;

import java.util.ArrayList;
import java.util.Random;

public class TextGenerator {

	private wordcategoryDAO categoryDAO;
	private wordpoolDAO wordDAO;

	private Random random;

	public TextGenerator() {

		categoryDAO = new wordcategoryDAO();
		wordDAO = new wordpoolDAO();

		random = new Random();
	}

	public String generateText(int targetLength) {

		StringBuilder sb = new StringBuilder();

		ArrayList<WordCategory> categories = categoryDAO.getAllCategories();

		while (sb.length() < targetLength) {

			String category = chooseCategory(categories);

			ArrayList<String> words = wordDAO.getWordsByCategory(category);

			if (!words.isEmpty()) {

				String word = words.get(random.nextInt(words.size()));

				sb.append(word).append(" ");
			}
		}

		return sb.toString().trim();
	}

	private String chooseCategory(ArrayList<WordCategory> categories) {

		double total = 0;

		for (WordCategory wc : categories) {

			total += wc.getRarity();
		}

		double value = random.nextDouble() * total;

		double cumulative = 0;

		for (WordCategory wc : categories) {

			cumulative += wc.getRarity();

			if (value <= cumulative) {

				return wc.getWordCategory();
			}
		}

		return categories.get(0).getWordCategory();
	}
}