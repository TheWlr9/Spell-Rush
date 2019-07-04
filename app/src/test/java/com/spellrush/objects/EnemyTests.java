package com.spellrush.objects;

import android.graphics.Canvas;

import junit.framework.TestCase;

import org.junit.Test;

public class EnemyTests extends TestCase {


    @Test
    public void test_alive_change_to_NullEnemy(){
        System.out.println("Ending Test- enemy alive state should change to false");
        Enemy enemy = Enemy.getInstance();
        enemy.setAI(new BasicEnemyAI());
        assertTrue(enemy.isAlive());
        assertTrue(enemy.getAI() instanceof BasicEnemyAI);
        enemy.getHit(enemy.getMaxHP());
        assertFalse(enemy.isAlive());
        assertTrue(enemy.getAI() instanceof NullEnemyAI);
        System.out.println("Ending Test- enemy alive state should change to false");
    }
}

