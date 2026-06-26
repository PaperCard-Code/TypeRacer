package dao;

import model.WordCategory;

import java.sql.*;
import java.util.ArrayList;

public class wordcategoryDAO {

	public boolean addCategory(WordCategory wc) {

		String sql = "INSERT INTO wordcategories(wordcategory,rarity)" + " VALUES(?,?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, wc.getWordCategory());
			ps.setDouble(2, wc.getRarity());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<WordCategory> getAllCategories() {

		ArrayList<WordCategory> list = new ArrayList<>();

		String sql = "SELECT * FROM wordcategories";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				WordCategory wc = new WordCategory();

				wc.setWordCategory(rs.getString("wordcategory"));

				wc.setRarity(rs.getDouble("rarity"));

				list.add(wc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean updateCategory(WordCategory wc) {

		String sql = "UPDATE wordcategories SET rarity=? WHERE wordcategory=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setDouble(1, wc.getRarity());
			ps.setString(2, wc.getWordCategory());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteCategory(String category) {

		String sql = "DELETE FROM wordcategories WHERE wordcategory=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, category);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}