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
        assert(result.isPlayerAttack);
        System.out.println(strPrintFinish + "test_createFireAttack_shouldCreatePlayerFireAttack");
    }

    @Test
    public void test_createFireAttack_shouldCreateEnemyFireAttack() {
        System.out.println(strPrintStart + "test_createFireAttack_shouldCreateEnemyFireAttack");
        AttackFactory.createFireAttack(true, 0);
        AttackObject result = LevelManager.getInstance().getGameBoard().getAttacksToAdd().remove();
        assert(result instanceof FireAttack);
        assert(result.isPlayerAttack);
        System.out.println(strPrintFinish + "test_createFireAttack_shouldCreateEnemyFireAttack");
    }

}
