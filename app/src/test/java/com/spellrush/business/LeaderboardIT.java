package com.spellrush.business;

import com.spellrush.application.ScoreEntry;
import com.spellrush.persistence.ILeaderboardPersistence;
import com.spellrush.persistence.hsqldb.LeaderboardHSQLDB;
import com.spellrush.services.Services;
import com.spellrush.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeaderboardIT {
    private LeaderboardController leaderboardController;
    private File tempDB;

    private static final String strPrintStart = "\nStarting LeaderboardIT: ";
    private static final String strPrintFinish = "\nFinished LeaderboardIT: ";

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final ILeaderboardPersistence persistence = new LeaderboardHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        leaderboardController = new LeaderboardController(persistence);
    }

    @Test
    public void test_insert_and_delete_score() {
        System.out.println(strPrintStart + "test_insert_and_delete_score");
        int initialSize = leaderboardController.getAllScoresSorted().size();

        leaderboardController.insertScore(new ScoreEntry("hunter2", 1000));
        assertEquals(initialSize + 1, leaderboardController.getAllScoresSorted().size());

        leaderboardController.deleteScore(new ScoreEntry("hunter2", 1000));
        assertEquals(initialSize, leaderboardController.getAllScoresSorted().size());

        System.out.println(strPrintFinish + "test_insert_and_delete_score");
    }

    @Test
    public void test_get_all_scores_sorted() {
        System.out.println(strPrintStart + "test_get_all_scores_sorted");
        int initialSize = leaderboardController.getAllScoresSorted().size();

        List<ScoreEntry> scores = leaderboardController.getAllScoresSorted();
        assertEquals(initialSize, scores.size());

        for (int i = 1; i <= 5; i++){
            leaderboardController.insertScore(new ScoreEntry("hunter"+i, 1000 * i));
        }

        scores = leaderboardController.getAllScoresSorted();
        assertEquals(initialSize+5, scores.size());
        // the first one should be the the highest ScoreEntry inserted
        assertEquals(scores.get(0), new ScoreEntry("hunter5", 5000));

        System.out.println(strPrintFinish + "test_get_all_scores_sorted");
    }

    @After
    public void destroy() {
        this.tempDB.delete();
    }
}
