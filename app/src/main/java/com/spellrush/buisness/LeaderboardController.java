package com.spellrush.buisness;

import com.spellrush.objects.ScoreEntry;
import com.spellrush.persistence.ILeaderboardPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardController {
    private ILeaderboardPersistence leaderboardPersistence;

    public LeaderboardController(final ILeaderboardPersistence leaderboardPersistence){
        this.leaderboardPersistence = leaderboardPersistence;
    }

    public List<ScoreEntry> getAllScoresSorted() {
        List<ScoreEntry> scoreEntries;
        scoreEntries = new ArrayList<>(leaderboardPersistence.getAllScores());
        Collections.sort(scoreEntries);
        return scoreEntries;
    } //end getAllScoresSorted()

    public boolean insertScore(ScoreEntry thisScoreEntry) {
        return leaderboardPersistence.insertScore(thisScoreEntry);
    } //end insertScore

    public boolean deleteScore(ScoreEntry thisScoreEntry) {
        return leaderboardPersistence.deleteScore(thisScoreEntry);
    } //end deleteScore
}
