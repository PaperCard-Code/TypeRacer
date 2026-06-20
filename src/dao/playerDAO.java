package dao;

import model.Player;

import java.sql.*;
import java.util.ArrayList;

public class playerDAO {

    // CREATE PLAYER
    public boolean registerPlayer(Player player) {

        String sql =
                "INSERT INTO players(name,email,password) VALUES(?,?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, player.getName());
            ps.setString(2, player.getEmail());
            ps.setString(3, player.getPassword());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // LOGIN

    public Player login(String email, String password) {

        String sql =
                "SELECT * FROM players WHERE email=? AND password=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1,email);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

            	Player p = new Player();

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));

                return p;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // READ ALL

    public ArrayList<Player> getAllPlayers() {

        ArrayList<Player> list = new ArrayList<>();

        String sql = "SELECT * FROM players";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

            	Player p = new Player();

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));

                list.add(p);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // UPDATE

    public boolean updatePlayer(Player player) {

        String sql =
            "UPDATE players SET name=?,email=?,password=? WHERE id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1,player.getName());
            ps.setString(2,player.getEmail());
            ps.setString(3,player.getPassword());
            ps.setInt(4,player.getId());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE

    public boolean deletePlayer(int id) {

        String sql = "DELETE FROM players WHERE id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1,id);

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public Player getPlayerById(int id) {

        String sql =
                "SELECT * FROM players WHERE id=?";

        try(Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                Player player =
                        new Player();

                player.setId(
                        rs.getInt("id"));

                player.setName(
                        rs.getString("name"));

                player.setEmail(
                        rs.getString("email"));

                player.setPassword(
                        rs.getString("password"));

                return player;
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}