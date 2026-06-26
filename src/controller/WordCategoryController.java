package controller;

import dao.wordcategoryDAO;
import model.WordCategory;

import java.util.ArrayList;

public class WordCategoryController {

	private wordcategoryDAO dao;

	public WordCategoryController() {

		dao = new wordcategoryDAO();
	}

	public boolean addCategory(String category, double rarity) {

		WordCategory wc = new WordCategory();

		wc.setWordCategory(category);

		wc.setRarity(rarity);

		return dao.addCategory(wc);
	}

	public ArrayList<WordCategory> getCategories() {

		return dao.getAllCategories();
	}

	public boolean updateCategory(WordCategory wc) {

		return dao.updateCategory(wc);
	}

	public boolean deleteCategory(String category) {

		return dao.deleteCategory(category);
	}

	public wordcategoryDAO getDAO() {

		return dao;
	}
}