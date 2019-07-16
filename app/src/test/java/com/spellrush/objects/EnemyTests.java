package com.spellrush.objects;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

public class EnemyTests extends TestCase {


    @Test
    public void test_alive_change_to_NullEnemy(){
        System.out.println("Starting Test- enemy alive state should change to false");
        Enemy enemy = Enemy.getInstance();
        enemy.setAI(new BasicEnemyAI());
        assertTrue(enemy.isAlive());
        assertTrue(enemy.getAI() instanceof BasicEnemyAI);
        enemy.getHit(enemy.getMaxHP());
        assertFalse(enemy.isAlive());
        assertTrue(enemy.getAI() instanceof NullEnemyAI);
        System.out.println("Ending Test- enemy alive state should change to false");
    }

    @Test
    public void test_update_increasesDrawTimer(){
        System.out.println("Starting Test- test_update_increasesDrawTimer");
        Enemy enemy = Enemy.getInstance();
        enemy.setAI(Mockito.mock(NullEnemyAI.class));
        int time = enemy.getDrawTimer();
        enemy.update();
        assertFalse(time == enemy.getDrawTimer());
        System.out.println("Ending Test- test_update_increasesDrawTimer");
    }
}

