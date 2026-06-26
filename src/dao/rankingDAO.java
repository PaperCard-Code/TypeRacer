package dao;

import model.Ranking;

import java.sql.*;
import java.util.ArrayList;

public class rankingDAO {

	public boolean insertRanking(Ranking ranking) {

		String sql = "INSERT INTO rankings(name,player_id,wpm,accuracy,test_date)" + " VALUES(?,?,?,?,?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, ranking.getPlayerName());
			ps.setInt(2, ranking.getPlayerId());
			ps.setDouble(3, ranking.getWpm());
			ps.setDouble(4, ranking.getAccuracy());

			ps.setTimestamp(5, new Timestamp(ranking.getTestDate().getTime()));

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<Ranking> getRankings() {

		ArrayList<Ranking> list = new ArrayList<>();

		String sql = "SELECT * FROM rankings " + "ORDER BY wpm DESC, accuracy DESC";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Ranking ranking = new Ranking();

				ranking.setId(rs.getInt("id"));
				ranking.setPlayerName(rs.getString("name"));
				ranking.setPlayerId(rs.getInt("player_id"));
				ranking.setWpm(rs.getDouble("wpm"));
				ranking.setAccuracy(rs.getDouble("accuracy"));
				ranking.setTestDate(rs.getTimestamp("test_date"));

				list.add(ranking);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean deleteRanking(int id) {

		String sql = "DELETE FROM rankings WHERE id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}