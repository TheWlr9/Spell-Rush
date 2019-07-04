package com.spellrush.persistence.stubs;

import com.spellrush.application.ScoreEntry;
import com.spellrush.persistence.ILeaderboardPersistence;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardPersistenceStub implements ILeaderboardPersistence {
    private List<ScoreEntry> scoreEntries;

    public LeaderboardPersistenceStub() {
        this.scoreEntries = new ArrayList<>();
        scoreEntries.add(new ScoreEntry("ayy",100));
        scoreEntries.add(new ScoreEntry("lmao",200));
        scoreEntries.add(new ScoreEntry("Margaret",500));
        scoreEntries.add(new ScoreEntry("hunter2",1000));
        scoreEntries.add(new ScoreEntry("Biden",9001));
        scoreEntries.add(new ScoreEntry("Joe",200));
    }

    @Override
    public List<ScoreEntry> getAllScores() {
        return scoreEntries;
    } //end getScores()

    @Override
    public boolean insertScore(ScoreEntry thisScoreEntry) {
        boolean isAdded;
        isAdded =  scoreEntries.add(thisScoreEntry);
        return isAdded;
    } //end insertScore

    @Override
    public boolean deleteScore(ScoreEntry thisScoreEntry) {
        boolean isRemoved;
        isRemoved = scoreEntries.remove(thisScoreEntry);
        return isRemoved;
    } //end deleteScore()
}
