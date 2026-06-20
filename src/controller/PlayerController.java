package controller;

import dao.playerDAO;
import model.Player;

import java.util.ArrayList;

public class PlayerController {

    private playerDAO playerDAO;

    public PlayerController() {
        playerDAO = new playerDAO();
    }

    public boolean registerPlayer(
            String name,
            String email,
            String password) {

        Player player = new Player();

        player.setName(name);
        player.setEmail(email);
        player.setPassword(password);

        return playerDAO.registerPlayer(player);
    }

    public Player login(
            String email,
            String password) {

        return playerDAO.login(email, password);
    }

    public ArrayList<Player> getAllPlayers() {

        return playerDAO.getAllPlayers();
    }

    public boolean updatePlayer(Player player) {

        return playerDAO.updatePlayer(player);
    }

    public boolean deletePlayer(int id) {

        return playerDAO.deletePlayer(id);
    }
}