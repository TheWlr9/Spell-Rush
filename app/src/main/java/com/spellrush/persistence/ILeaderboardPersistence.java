package com.spellrush.persistence;
import java.util.List;

import com.spellrush.application.ScoreEntry;

public interface ILeaderboardPersistence {
    List<ScoreEntry> getAllScores();
    boolean insertScore(ScoreEntry thisScoreEntry);
    boolean deleteScore(ScoreEntry thisScoreEntry);
}
