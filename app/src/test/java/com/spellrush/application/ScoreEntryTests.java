package com.spellrush.application;

import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreEntryTests {
    @Test
    public void verifyScoreIsCreated() {
        ScoreEntry scoreEntry1, scoreEntry2;

        System.out.println("\nStarting Test - ScoreEntry - Verify that a scoreEntry instance is created");
        scoreEntry1 = new ScoreEntry("Earl", 500);
        assertNotNull(scoreEntry1);
        assertTrue("Earl".equals(scoreEntry1.getPlayerName()));
        assertTrue(500 == scoreEntry1.getPlayerScore());

        scoreEntry2 = new ScoreEntry("Joe", 501);
        assertTrue(scoreEntry2.compareTo(scoreEntry1) < 0);

        System.out.println("Finished Test - ScoreEntry - Verify that a scoreEntry instance is created");
    } //end verifyScoreIsCreated()
}
