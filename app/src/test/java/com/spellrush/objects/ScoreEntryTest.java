package com.spellrush.objects;

import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreEntryTest {
    @Test
    public void verifyScoreIsCreated() {
        ScoreEntry scoreEntry;

        System.out.println("\nStarting Test - ScoreEntry - Verify that a scoreEntry instance is created");
        scoreEntry = new ScoreEntry("Earl", 500);
        assertNotNull(scoreEntry);
        assertTrue("Earl".equals(scoreEntry.getPlayerName()));
        assertTrue(500 == scoreEntry.getPlayerScore());

        System.out.println("Finished Test - ScoreEntry - Verify that a scoreEntry instance is created");
    } //end verifyScoreIsCreated()
}
