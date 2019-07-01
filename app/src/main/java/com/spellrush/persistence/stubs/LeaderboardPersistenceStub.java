package com.spellrush.persistence.stubs;

import com.spellrush.application.ScoreEntry;
import com.spellrush.persistence.ILeaderboardPersistence;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardPersistenceStub implements ILeaderboardPersistence {
    private List<ScoreEntry> scoreEntries;

    public LeaderboardPersistenceStub() {
        this.scoreEntries = new ArrayList<>();
        scoreEntries.add(new ScoreEntry("joe",20));
        scoreEntries.add(new ScoreEntry("biden",40));
        scoreEntries.add(new ScoreEntry("edwardo", 123));
        scoreEntries.add(new ScoreEntry("bobbie",500));
        scoreEntries.add(new ScoreEntry("domingo",9001));
        scoreEntries.add(new ScoreEntry("bev", 92103));
        scoreEntries.add(new ScoreEntry("clementina",42));
        scoreEntries.add(new ScoreEntry("ron",8008));
        scoreEntries.add(new ScoreEntry("123109qwei", 0));
        scoreEntries.add(new ScoreEntry("AAAAAAAAAAAAAAA",1));
        scoreEntries.add(new ScoreEntry("STUB", 70707));
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
