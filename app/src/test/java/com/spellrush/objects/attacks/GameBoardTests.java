package com.spellrush.objects.attacks;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;



public class GameBoardTests extends TestCase {

    private static final String strPrintStart = "\nStarting testGameBoard: ";
    private static final String strPrintFinish = "\nFinished testGameBoard: ";

    static final int maxObjects = 3;
    static final int numLanes = 3;

    GameBoard stubBoard;

    public GameBoardTests(String arg0){
        super(arg0);
        GameBoard stubBoard = new GameBoard(1, 0, 1,maxObjects);
    }

    @Before
    private void setup(){
        stubBoard = new GameBoard(numLanes, 0, 1,maxObjects);
        assert(stubBoard.getAttacks().size() == 0);
    }

    @Test
    public void test_addAttack_shouldAddAttack() {
        System.out.println(strPrintStart + "test_addAttack_shouldAddAttack");
        setup();
        stubBoard.addAttack(GameBoard.AttackType.Fire,true,0,10,10);

        assert(stubBoard.getAttacksToAdd().size() == 1);
        AttackObject result = stubBoard.getAttacksToAdd().remove();
        assert(result instanceof FireAttack);

        System.out.println(strPrintFinish + "test_addAttack_shouldAddAttack");
    }

    @Test
    public void test_addAttack_shouldAllowMaxBullets() {
        System.out.println(strPrintStart + "test_addAttack_shouldNotExceedMaxBullets");
        setup();
        for (int i = 0; i < maxObjects; i++) {
            stubBoard.addAttack(GameBoard.AttackType.Fire,true,0,10,10);
        }
        assert(stubBoard.getAttacksToAdd().size() == maxObjects);
        System.out.println(strPrintFinish + "test_addAttack_shouldNotExceedMaxBullets");
    }

    @Test
    public void test_addAttack_shouldNotExceedMaxBullets() {
        System.out.println(strPrintStart + "test_addAttack_shouldNotExceedMaxBullets");

        setup();

        // Setup
        for (int i = 0; i < maxObjects; i++) {
            stubBoard.addAttack(GameBoard.AttackType.Fire,true,0,10,10);
        }
        stubBoard.addAttack(GameBoard.AttackType.Fire,true,0,10,10);
        assert(stubBoard.getAttacksToAdd().size() == maxObjects);

        System.out.println(strPrintFinish + "test_addAttack_shouldNotExceedMaxBullets");
    }

    @Test
    public void test_addAttack_LaneIndexShouldNotExceedNumLanes() {
        System.out.println(strPrintStart + "test_addAttack_LaneIndexShouldNotExceedNumLanes");
        setup();

        stubBoard.addAttack(GameBoard.AttackType.Fire,true,numLanes + 1,10,10);
        assert(stubBoard.getAttacksToAdd().size() == 0);

        System.out.println(strPrintFinish + "test_addAttack_LaneIndexShouldNotExceedNumLanes");
    }

    @Test
    public void test_addAttack_LaneIndexShouldNotBeNegative() {
        System.out.println(strPrintStart + "test_addAttack_LaneIndexShouldNotExceedNumLanes");
        setup();

        stubBoard.addAttack(GameBoard.AttackType.Fire,true,-1,10,10);
        assert(stubBoard.getAttacksToAdd().size() == 0);

        System.out.println(strPrintFinish + "test_addAttack_LaneIndexShouldNotExceedNumLanes");
    }

    @Test
    public void test_update_shouldAddAddedAttacks() {
        System.out.println(strPrintStart + "test_update_shouldAddAddedAttacks");

        setup();

        stubBoard.addAttack(GameBoard.AttackType.Fire,true,0,10,10);
        stubBoard.update();
        assert(stubBoard.getAttacks().size() == 1);

        System.out.println(strPrintFinish + "test_update_shouldAddAddedAttacks");
    }

    @Test
    public void test_clear_shouldClearAllAttacksOnUpdate() {
        System.out.println(strPrintStart + "test_clear_shouldClearAllAttacksOnUpdate");

        setup();

        // setup
        stubBoard.addAttack(GameBoard.AttackType.Fire,true,0,10,10);
        stubBoard.addAttack(GameBoard.AttackType.Fire,true,0,10,10);
        stubBoard.update();
        stubBoard.addAttack(GameBoard.AttackType.Fire,true,0,10,10);
        // test
        stubBoard.clear();
        stubBoard.update();
        assert(stubBoard.getAttacks().isEmpty());
        assert(stubBoard.getAttacksToAdd().isEmpty());
        assert(stubBoard.getAttacksToDelete().isEmpty());

        System.out.println(strPrintFinish + "test_clear_shouldClearAllAttacksOnUpdate");
    }
}
