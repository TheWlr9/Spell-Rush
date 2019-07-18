package com.spellrush.business;

import android.media.MediaPlayer;

import com.spellrush.business.LevelManager.Level;
import com.spellrush.business.LevelManager.LevelManager;
import com.spellrush.objects.Enemy;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

public class GameLevelIT extends TestCase {
    private static final String strPrintStart = "\nStarting testLevelManager: ";
    private static final String strPrintFinish = "\nFinished testLevelManager: ";

    private static final MediaPlayer mp = Mockito.mock(MediaPlayer.class);

    @Test
    public void test_createNewGameLevel() {
        System.out.println(strPrintStart + "test_createNewGameLevel");

        LevelManager level = LevelManager.getInstance();
        assertEquals(Enemy.getInstance().getAI(), Level.LEVEL_1.getEnemy());
        assertEquals(level.getGameBoard().getAttacks().size(), 0);

        System.out.println(strPrintFinish + "test_createNewGameLevel");
    }

    @Test
    public void test_levelUpdate_progressesLevel() {
        System.out.println(strPrintStart + "test_createNewGameLevel");

        LevelManager level = LevelManager.getInstance();
        for(int i = 0; i < 30; i++){
            level.update();
        }
        assertEquals(level.getGameBoard().getAttacks().size(), 0);
        for(int i = 0; i < 60; i++){
            level.update();
        }
        assertTrue(PlayerController.getInstance().getHP() < PlayerController.getInstance().getMaxHP());
        System.out.println(strPrintFinish + "test_createNewGameLevel");
    }
}