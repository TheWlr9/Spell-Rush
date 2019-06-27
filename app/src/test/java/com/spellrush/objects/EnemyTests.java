package com.spellrush.objects;

import android.graphics.Canvas;

import junit.framework.TestCase;

import org.junit.Test;

public class EnemyTests extends TestCase {

    public static final int testMaxHP = 100;

    public class testEnemy extends Enemy{
        public testEnemy(int x, int y, int depth, int framesBetweenAttacks) {
            super(x, y, depth, framesBetweenAttacks, testMaxHP);
        }

        @Override
        public void doAttack() {
            return;
        }

        @Override
        public void draw(Canvas canvas) { return; }
    }

    Enemy enemy = new testEnemy(100,100,100,60);

    @Test
    public void test_alive_change_to_dead_at_hp_zero(){
        System.out.println("Ending Test- enemy alive state should change to false");
        enemy = new testEnemy(0,0,0,0); // Reset HP
        assertTrue(enemy.isAlive());
        enemy.getHit(testMaxHP + 5);
        assertFalse(enemy.isAlive());
        System.out.println("Ending Test- enemy alive state should change to false");
    }
}