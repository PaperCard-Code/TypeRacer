package dao;

import model.WordPool;

import java.sql.*;
import java.util.ArrayList;

public class wordpoolDAO {

	public boolean addWord(WordPool wp) {

		String sql = "INSERT INTO wordpool(word,category) VALUES(?,?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, wp.getWord());
			ps.setString(2, wp.getCategory());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<WordPool> getAllWords() {

		ArrayList<WordPool> list = new ArrayList<>();

		String sql = "SELECT * FROM wordpool";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				WordPool wp = new WordPool();

				wp.setWord(rs.getString("word"));
				wp.setCategory(rs.getString("category"));

				list.add(wp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean updateWord(WordPool wp, String oldWord) {

		String sql = "UPDATE wordpool SET word=?,category=? WHERE word=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, wp.getWord());
			ps.setString(2, wp.getCategory());
			ps.setString(3, oldWord);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteWord(String word) {

		String sql = "DELETE FROM wordpool WHERE word=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, word);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<String> getWordsByCategory(String category) {

		ArrayList<String> words = new ArrayList<>();

		String sql = "SELECT word FROM wordpool WHERE category=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, category);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				words.add(rs.getString("word"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return words;
	}
}