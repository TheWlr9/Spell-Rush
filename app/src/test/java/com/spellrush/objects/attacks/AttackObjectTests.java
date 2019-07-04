package com.spellrush.objects.attacks;

import android.graphics.Canvas;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttackObjectTests {

    private static final String strPrintStart = "\nStarting testGameBoard: ";
    private static final String strPrintFinish = "\nFinished testGameBoard: ";

    public final int dmg = 10;
    public final int spd = 5;
    public final int laneStart = 0;
    public final int laneEnd = 200;

    AttackObject pStubAttack;
    AttackObject eStubAttack;

    class StubAttackObject extends AttackObject {

        public StubAttackObject(boolean isPlayerAttack){
            super(isPlayerAttack, 0,spd,laneStart,laneEnd,dmg, AttackType.Fire);
        }

        @Override
        public void draw(Canvas canvas) {
            return;
        }
    }

    @Before
    public void setUp() {
        pStubAttack = new StubAttackObject(true);
        eStubAttack = new StubAttackObject(false);
    }

    @Test
    public void test_YPosition_should_initToStart() {
        System.out.println(strPrintStart + "test_getYPosition_shouldGetYPos");
        setUp();
        assertEquals(laneEnd, pStubAttack.getYPosition());
        System.out.println(strPrintFinish + "test_getYPosition_shouldGetYPos");
    }

    @Test
    public void test_speed_shouldBeNegativeForPlayer() {
        System.out.println(strPrintStart + "test_speed_shouldBeNegativeForPlayer");
        setUp();
        assertEquals(-1 * spd, pStubAttack.getSpeed());
        System.out.println(strPrintFinish + "test_speed_shouldBeNegativeForPlayer");
    }

    @Test
    public void test_speed_shouldBePositiveForEnemy() {
        System.out.println(strPrintStart + "test_speed_shouldBePositiveForEnemy");
        setUp();
        assertEquals(spd, eStubAttack.getSpeed());
        System.out.println(strPrintFinish + "test_speed_shouldBePositiveForEnemy");
    }

    @Test
    public void test_wasDestroyed_shouldBeFalseOnInit() {
        System.out.println(strPrintStart + "test_wasDestroyed_shouldBeFalseOnInit");
        setUp();
        assertFalse(pStubAttack.wasDestroyed());
        System.out.println(strPrintFinish + "test_wasDestroyed_shouldBeFalseOnInit");
    }

    @Test
    public void test_reachedEnd_shouldBeFalseOnInit(){
        System.out.println(strPrintStart + "test_reachedEnd_shouldBeFalseOnInit");
        setUp();
        assertFalse(pStubAttack.reachedEnd());
        System.out.println(strPrintFinish+ "test_reachedEnd_shouldBeFalseOnInit");
    }

    @Test
    public void test_hasSameAllegiance_shouldBeFalseForEnemyAndPlayer(){
        System.out.println(strPrintStart + "test_hasSameAllegiance_shouldBeFalseForEnemyAndPlayer");
        setUp();
        assertFalse(pStubAttack.hasSameAllegiance(eStubAttack));
        System.out.println(strPrintFinish+ "test_hasSameAllegiance_shouldBeFalseForEnemyAndPlayer");
    }

    @Test
    public void test_isPlayerAttack_shouldBeTrueForPlayerAttack(){
        System.out.println(strPrintStart + "test_isPlayerAttack_shouldBeTrueForPlayerAttack");
        setUp();
        assertTrue(pStubAttack.isPlayerAttack());
        System.out.println(strPrintFinish+ "test_isPlayerAttack_shouldBeTrueForPlayerAttack");
    }

    @Test
    public void test_isPlayerAttack_shouldBeFalseForEnemy(){
        System.out.println(strPrintStart + "test_isPlayerAttack_shouldBeFalseForEnemy");
        setUp();
        assertFalse(eStubAttack.isPlayerAttack());
        System.out.println(strPrintFinish+ "test_isPlayerAttack_shouldBeFalseForEnemy");

    }
}