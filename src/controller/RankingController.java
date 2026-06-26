package controller;

import dao.rankingDAO;
import model.Ranking;

import java.util.ArrayList;

public class RankingController {

    private rankingDAO rankingDAO;

    public RankingController() {

        rankingDAO = new rankingDAO();
    }

    public boolean saveRanking(
            Ranking ranking) {

        return rankingDAO.insertRanking(ranking);
    }

    public ArrayList<Ranking> getRankings() {

        return rankingDAO.getRankings();
    }

    public boolean deleteRanking(int id) {

        return rankingDAO.deleteRanking(id);
    }
}