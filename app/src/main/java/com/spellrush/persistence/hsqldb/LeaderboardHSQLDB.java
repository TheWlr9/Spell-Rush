package com.spellrush.persistence.hsqldb;

import com.spellrush.application.ScoreEntry;
import com.spellrush.persistence.ILeaderboardPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardHSQLDB implements ILeaderboardPersistence {

    private Connection databaseConnection;

    public LeaderboardHSQLDB (final String path) {
        try {
            this.databaseConnection = DriverManager.getConnection("jdbc:hsqldb:file:" + path, "SA", "");
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Get all scores from the table
    public List<ScoreEntry> getAllScores() {
        final String queryString = "SELECT * FROM leaderboard";

        List<ScoreEntry> scoreList = new ArrayList<>();
        PreparedStatement query;
        ScoreEntry currScore;
        ResultSet results;

        try {
            query = databaseConnection.prepareStatement(queryString);
            results = query.executeQuery();

            while(results.next()) {
                currScore = resultsTransformer(results);
                scoreList.add(currScore);
            }

            results.close();
            query.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return scoreList;
    }

    //Insert the ScoreEntry into the table
    public boolean insertScore(ScoreEntry thisScoreEntry) {
        final String queryString = "INSERT INTO leaderboard(name, score) VALUES (?, ?)";
        PreparedStatement query;
        boolean isInserted = false;

        try {
            query = databaseConnection.prepareStatement(queryString);

            query.setString(1, thisScoreEntry.getPlayerName());
            query.setInt(2, thisScoreEntry.getPlayerScore());

            if(query.executeUpdate() > 0){
                isInserted = true;
            }

            query.close();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isInserted;
    }

    //Delete the ScoreEntry from the table
    public boolean deleteScore(ScoreEntry thisScoreEntry) {
        final String queryString = "DELETE FROM leaderboard WHERE name = ? AND score = ?";
        PreparedStatement query;
        boolean isDeleted = false;

        try {
            query = databaseConnection.prepareStatement(queryString);

            query.setString(1, thisScoreEntry.getPlayerName());
            query.setInt(2, thisScoreEntry.getPlayerScore());

            if(query.executeUpdate() > 0){
                isDeleted = true;
            }

            query.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isDeleted;
    }


    //Transform the result into a ScoreEntry
    private ScoreEntry resultsTransformer(ResultSet results){
        ScoreEntry entry;
        String playerName;
        int playerScore;

        try {
            playerName = results.getString("name");
            playerScore = results.getInt("score");
            entry = new ScoreEntry(playerName, playerScore);
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }

        return entry;
    }


}
