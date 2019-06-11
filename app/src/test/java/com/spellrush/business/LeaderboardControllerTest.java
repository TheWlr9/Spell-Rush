package com.spellrush.business;

import com.spellrush.objects.Services;
import com.spellrush.application.ScoreEntry;
import com.spellrush.persistence.ILeaderboardPersistence;

import org.junit.Test;

import static junit.framework.Assert.*;

public class LeaderboardControllerTest {

    @Test
    public void verifyListIsSorted() {
        int initialCount, highestScore;

        System.out.println("Starting Test -  Leaderboard Controller Test - Verify that the returned list is sorted");
        LeaderboardController controller = new LeaderboardController(Services.getLeaderboardPersistence());
        ILeaderboardPersistence data = Services.getLeaderboardPersistence();
        assertNotNull(controller);
        assertNotNull(data);

        highestScore = data.getAllScores().get(0).getPlayerScore();
        initialCount = data.getAllScores().size();

        System.out.println("The current highest score is " + highestScore + ".");

        System.out.println("Inserting score: " + (highestScore+1) + ".");
        controller.insertScore(new ScoreEntry("New Highest", highestScore+1));
        assertTrue(initialCount == data.getAllScores().size()-1);
        controller.deleteScore(new ScoreEntry("New Highest", highestScore+1));
        assertTrue((initialCount == data.getAllScores().size()));

        System.out.println("Finished Test - Leaderboard Controller Test - Verify that the returned list is sorted");
    } //end verifyListIsSorted()

}
