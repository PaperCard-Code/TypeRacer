package dao;

import model.Admin;

import java.sql.*;
import java.util.ArrayList;

public class adminDAO {

	public boolean addAdmin(Admin admin) {

		String sql = "INSERT INTO admins(name,email,password) VALUES(?,?,?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, admin.getName());
			ps.setString(2, admin.getEmail());
			ps.setString(3, admin.getPassword());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public Admin login(String email, String password) {

		String sql = "SELECT * FROM admins WHERE email=? AND password=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Admin a = new Admin();

				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setEmail(rs.getString("email"));
				a.setPassword(rs.getString("password"));

				return a;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Admin> getAllAdmins() {

		ArrayList<Admin> list = new ArrayList<>();

		String sql = "SELECT * FROM admins";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Admin a = new Admin();

				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setEmail(rs.getString("email"));
				a.setPassword(rs.getString("password"));

				list.add(a);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean updateAdmin(Admin admin) {

		String sql = "UPDATE admins SET name=?,email=?,password=? WHERE id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, admin.getName());
			ps.setString(2, admin.getEmail());
			ps.setString(3, admin.getPassword());
			ps.setInt(4, admin.getId());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteAdmin(int id) {

		String sql = "DELETE FROM admins WHERE id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public Admin getAdminById(int id) {

		String sql = "SELECT * FROM admins WHERE id=?";

		try (Connection con = DBConnection.getConnection();

				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Admin admin = new Admin();

				admin.setId(rs.getInt("id"));

				admin.setName(rs.getString("name"));

				admin.setEmail(rs.getString("email"));

				admin.setPassword(rs.getString("password"));

				return admin;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}
}