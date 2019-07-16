package com.spellrush.business;

import com.spellrush.business.LevelManager.LevelManager;
import com.spellrush.objects.BasicEnemyAI;
import com.spellrush.objects.Enemy;
import com.spellrush.objects.NullEnemyAI;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Before;

public class LevelManagerTests extends TestCase {

    private static final String strPrintStart = "\nStarting testLevelManager: ";
    private static final String strPrintFinish = "\nFinished testLevelManager: ";

    Enemy enemy;
    LevelManager testManager;

    @Before
    public void setUp() {
        enemy = Enemy.getInstance();
        testManager = LevelManager.getInstance();
    }

    @Test
    public void test_setCurrentEnemyAI_shouldSetCurrentEnemyAI() {
        System.out.println(strPrintStart + "test_setCurrentEnemy_shouldSetCurrentEnemy");
        testManager.reset();
        testManager.setCurrentEnemyAI(new BasicEnemyAI());
        assertTrue(enemy.getAI() instanceof BasicEnemyAI);

        System.out.println(strPrintFinish + "test_setCurrentEnemy_shouldSetCurrentEnemy");
    }

    @Test
    public void test_update_should_DestroyEnemyOnDeath() {
        System.out.println(strPrintStart + "test_update_should_DestroyEnemyOnDeath");
        testManager.update();
        enemy.getHit(enemy.getMaxHP());
        assertTrue(enemy.getAI() instanceof NullEnemyAI);
        System.out.println(strPrintFinish + "test_update_should_DestroyEnemyOnDeath");
    }
}
