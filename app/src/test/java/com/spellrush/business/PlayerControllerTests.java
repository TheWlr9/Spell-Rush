package com.spellrush.business;

import junit.framework.TestCase;

import org.junit.Test;

public class PlayerControllerTests extends TestCase {

    private PlayerController testPlayer;
    private static final String strPrintStart = "\nStarting testPlayerController: ";
    private static final String strPrintFinish = "\nFinished testPlayerController: ";

    public PlayerControllerTests(String arg0){
        super(arg0);
        testPlayer = new PlayerController();
    }

    @Test
    public void test_addHp_should_notExceedMaxHP() {
        System.out.println(strPrintStart + "addHp_should_notExceedMaxHP");

        testPlayer.addHP(testPlayer.MAX_HP);
        assertEquals(testPlayer.MAX_HP, testPlayer.getHP());

        testPlayer.addHP(999999999);
        assertEquals(testPlayer.MAX_HP, testPlayer.getHP());

        System.out.println(strPrintFinish + "addHp_should_notExceedMaxHP");
    }

    @Test
    public void test_loseHP_should_notGoLowerThanZero() {
        System.out.println(strPrintStart + "loseHP_should_notGoLowerThanZero");

        testPlayer.loseHP(testPlayer.MAX_HP);
        assertEquals(0, testPlayer.getHP());

        testPlayer.loseHP(999999999);
        assertEquals(0, testPlayer.getHP());

        System.out.println(strPrintFinish + "loseHP_should_notGoLowerThanZero");
    }

    @Test
    public void test_loseHP_should_decrementHP() {
        System.out.println(strPrintStart + "loseHP_should_decrementHP");

        // Add HP first
        testPlayer.addHP(5);

        int before = testPlayer.getHP();
        testPlayer.loseHP(5);
        assertEquals(testPlayer.getHP(), before - 5);

        System.out.println(strPrintFinish + "loseHP_should_decrementHP");
    }

    @Test
    public void test_addHp_should_incrementHP(){
        System.out.println(strPrintStart + "addHp_should_incrementHP");

        // Lose HP first
        testPlayer.loseHP(5);

        int before = testPlayer.getHP();
        testPlayer.addHP(5);
        assertEquals(testPlayer.getHP(), before + 5);

        System.out.println(strPrintFinish + "addHp_should_incrementHP");
    }
}
