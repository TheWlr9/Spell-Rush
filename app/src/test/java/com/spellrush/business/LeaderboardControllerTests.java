package com.spellrush.business;

import com.spellrush.services.Services;
import com.spellrush.application.ScoreEntry;
import com.spellrush.persistence.ILeaderboardPersistence;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LeaderboardControllerTests {
    private static final String strPrintStart = "\nStarting LeaderBoardControllerTests: ";
    private static final String strPrintFinish = "\nFinished LeaderBoardControllerTests: ";

    private LeaderboardController leaderboardController;
    private ILeaderboardPersistence leaderboardPersistence;

    @Before
    public void setUp() {
        leaderboardPersistence = mock(ILeaderboardPersistence.class);
        leaderboardController = new LeaderboardController(leaderboardPersistence);
    }


    @Test
    public void test_mocked_getAllScoresSorted(){
        System.out.println(strPrintStart + "test_mocked_getAllScores");

        final ScoreEntry entry = new ScoreEntry("TEST", 9999999);
        final List<ScoreEntry> copy = new ArrayList<>();
        copy.add(entry);

        try {
            when(leaderboardPersistence.insertScore(entry)).thenReturn(copy.add(entry));
            when(leaderboardPersistence.deleteScore(entry)).thenReturn(copy.remove(entry));
            when(leaderboardPersistence.getAllScores()).thenReturn(copy);

            assertTrue(leaderboardController.insertScore(entry));
            assertTrue(leaderboardController.getAllScoresSorted().size() > 0);
            assertTrue(leaderboardController.deleteScore(entry));
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println(strPrintFinish + "test_mocked_getAllScores");
    }

}
