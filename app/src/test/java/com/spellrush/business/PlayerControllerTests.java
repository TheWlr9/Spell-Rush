package com.spellrush.business;

import junit.framework.TestCase;

import org.junit.Test;

public class PlayerControllerTests extends TestCase {

    private PlayerController testPlayer;
    private static final String strPrintStart = "\nStarting testPlayerController: ";
    private static final String strPrintFinish = "\nFinished testPlayerController: ";

    public PlayerControllerTests(String arg0){
        super(arg0);
        testPlayer = PlayerController.getInstance();
    }

    @Test
    public void test_addScore_should_incrementScore() {
        System.out.println(strPrintStart + "addScore_should_incrementScore");

        int before = testPlayer.getScore();
        testPlayer.addScore(5);
        assertEquals(testPlayer.getScore(), before + 5);

        System.out.println(strPrintFinish + "addScore_should_incrementScore");
    }

    @Test
    public void test_addScore_should_notAddNegativeScore() {
        System.out.println(strPrintStart + "addScore_should_incrementScore");

        int before = testPlayer.getScore();
        testPlayer.addScore(-5);
        assertEquals(testPlayer.getScore(), before);

        System.out.println(strPrintFinish + "addScore_should_incrementScore");
    }
}
