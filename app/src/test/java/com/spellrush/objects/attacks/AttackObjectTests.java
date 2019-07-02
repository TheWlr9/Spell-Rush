package com.spellrush.objects.attacks;

import android.graphics.Canvas;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttackObjectTests {

    private static final String strPrintStart = "\nStarting testGameBoard: ";
    private static final String strPrintFinish = "\nFinished testGameBoard: ";

    public final int dmg = 10;
    public final int spd = 10;
    public final int laneStart = 0;
    public final int laneEnd = 19;

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
        assertEquals(laneStart, pStubAttack.getYPosition());
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
}
