package com.spellrush.business;

import android.graphics.Canvas;

import com.spellrush.objects.Enemy;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Before;

public class LevelManagerTests extends TestCase {

    private static final String strPrintStart = "\nStarting testLevelManager: ";
    private static final String strPrintFinish = "\nFinished testLevelManager: ";

    public static final int testMaxHP = 10;
    public class testEnemy extends Enemy {
        public testEnemy(int x, int y, int depth, int framesBetweenAttacks) {
            super(x, y, depth, framesBetweenAttacks, 10);
        }

        @Override
        public void doAttack() { return; }

        @Override
        public void draw(Canvas canvas) { return; }
    }

    testEnemy stubEnemy;
    LevelManager testManager;

    @Before
    public void setUp() {
        stubEnemy  = new testEnemy(0, 0, 0, 0);
        testManager = LevelManager.getInstance();
    }

    @Test
    public void test_setCurrentEnemy_shouldSetCurrentEnemy() {
        System.out.println(strPrintStart + "test_setCurrentEnemy_shouldSetCurrentEnemy");

        testManager.setCurrentEnemy(stubEnemy);
        assertEquals(testManager.getCurrentEnemy(), stubEnemy);

        System.out.println(strPrintFinish + "test_setCurrentEnemy_shouldSetCurrentEnemy");
    }

    @Test
    public void test_update_should_DestroyEnemyOnDeath() {
        System.out.println(strPrintStart + "test_setCurrentEnemy_shouldSetCurrentEnemy");

        testManager.setCurrentEnemy(stubEnemy);
        stubEnemy.getHit(testMaxHP);
        testManager.update();
        assertNull(testManager.getCurrentEnemy());
        System.out.println(strPrintFinish + "test_setCurrentEnemy_shouldSetCurrentEnemy");
    }
}