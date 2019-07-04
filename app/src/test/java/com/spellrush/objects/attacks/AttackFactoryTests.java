package com.spellrush.objects.attacks;

import com.spellrush.business.LevelManager;

import junit.framework.TestCase;

import org.junit.Test;


public class AttackFactoryTests extends TestCase {

    private static final String strPrintStart = "\nStarting testGameBoard: ";
    private static final String strPrintFinish = "\nFinished testGameBoard: ";

    public AttackFactoryTests(String arg0){
        super(arg0);
    }

    @Test
    public void test_createFireAttack_shouldCreatePlayerFireAttack() {
        System.out.println(strPrintStart + "test_createFireAttack_shouldCreatePlayerFireAttack");
        AttackFactory.createFireAttack(true, 0);
        AttackObject result = LevelManager.getInstance().getGameBoard().getAttacksToAdd().remove();
        assert(result instanceof FireAttack);
        assert(result.isPlayerAttack());
        System.out.println(strPrintFinish + "test_createFireAttack_shouldCreatePlayerFireAttack");
    }

    @Test
    public void test_createWaterAttack_shouldCreatePlayerWaterAttack() {
        System.out.println(strPrintStart + "test_createWaterAttack_shouldCreatePlayerWaterAttack");
        AttackFactory.createWaterAttack(true, 0);
        AttackObject result = LevelManager.getInstance().getGameBoard().getAttacksToAdd().remove();
        assert(result instanceof WaterAttack);
        assert(result.isPlayerAttack());
        System.out.println(strPrintFinish + "test_createWaterAttack_shouldCreatePlayerWaterAttack");
    }

    @Test
    public void test_createGroundAttack_shouldCreatePlayerGroundAttack() {
        System.out.println(strPrintStart + "test_createGroundAttack_shouldCreatePlayerGroundAttack");
        AttackFactory.createGroundAttack(true, 0);
        AttackObject result = LevelManager.getInstance().getGameBoard().getAttacksToAdd().remove();
        assert(result instanceof GroundAttack);
        assert(result.isPlayerAttack());
        System.out.println(strPrintFinish + "test_createGroundAttack_shouldCreatePlayerGroundAttack");
    }


    @Test
    public void test_createFireAttack_shouldCreateEnemyFireAttack() {
        System.out.println(strPrintStart + "test_createFireAttack_shouldCreateEnemyFireAttack");
        AttackFactory.createFireAttack(false, 0);
        AttackObject result = LevelManager.getInstance().getGameBoard().getAttacksToAdd().remove();
        assert(result instanceof FireAttack);
        assert(!result.isPlayerAttack());
        System.out.println(strPrintFinish + "test_createFireAttack_shouldCreateEnemyFireAttack");
    }

    @Test
    public void test_createWaterAttack_shouldCreateEnemyWaterAttack() {
        System.out.println(strPrintStart + "test_createWaterAttack_shouldCreateEnemyWaterAttack");
        AttackFactory.createWaterAttack(false, 0);
        AttackObject result = LevelManager.getInstance().getGameBoard().getAttacksToAdd().remove();
        assert(result instanceof WaterAttack);
        assert(!result.isPlayerAttack());
        System.out.println(strPrintFinish + "test_createWaterAttack_shouldCreateEnemyWaterAttack");
    }

    @Test
    public void test_createGroundAttack_shouldCreateEnemyGroundAttack() {
        System.out.println(strPrintStart + "test_createGroundAttack_shouldCreateEnemyGroundAttack");
        AttackFactory.createGroundAttack(false, 0);
        AttackObject result = LevelManager.getInstance().getGameBoard().getAttacksToAdd().remove();
        assert(result instanceof GroundAttack);
        assert(!result.isPlayerAttack());
        System.out.println(strPrintFinish + "test_createGroundAttack_shouldCreateEnemyGroundAttack");
    }

}
