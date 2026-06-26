package controller;

import dao.wordpoolDAO;
import model.WordPool;

import java.util.ArrayList;

public class WordPoolController {

    private wordpoolDAO dao;

    public WordPoolController() {

        dao = new wordpoolDAO();
    }

    public boolean addWord(
            String word,
            String category) {

        WordPool wp =
                new WordPool();

        wp.setWord(word);
        wp.setCategory(category);

        return dao.addWord(wp);
    }

    public ArrayList<WordPool> getWords() {

        return dao.getAllWords();
    }

    public boolean updateWord(
            WordPool wp,
            String oldWord) {

        return dao.updateWord(
                wp,
                oldWord);
    }

    public boolean deleteWord(
            String word) {

        return dao.deleteWord(word);
    }
    
    public wordpoolDAO getDAO() {

        return dao;
    }
}