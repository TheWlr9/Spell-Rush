package com.spellrush.application;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyTest {

    Enemy enemy = new Enemy(100,100,100,60);

    @Test
    public void test_getHit_hp_should_reduce_min_zero() {
        System.out.println("Starting Test- enemy hp should not go lower than zero");
        int startHP=enemy.getHP();
        for(int i=0;i<enemy.MAX_HP+5;i++) {
            enemy.getHit();
        }
        assertTrue(enemy.getHP()>=0);
        assertTrue(enemy.getHP()<startHP);
    System.out.println("Ending Test- enemy hp should not go lower than zero");
    }

    @Test
    public void test_alive_change_to_dead_at_hp_zero(){
        System.out.println("Ending Test- enemy alive state should change to false");
        assertTrue(enemy.isAlive());
        for(int i=0;i<enemy.MAX_HP;i++) {
            enemy.getHit();
        }
        assertFalse(enemy.isAlive());
        System.out.println("Ending Test- enemy alive state should change to false");
    }
}