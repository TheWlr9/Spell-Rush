package com.spellrush.objects.attacks;

import com.spellrush.business.PlayerController;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;


public class AttackCollisionsUtilityTests extends TestCase {

    private static final String strPrintStart = "\nStarting AttackCollisionsUtilityTests: ";
    private static final String strPrintFinish = "\nFinished AttackCollisionsUtilityTests: ";

    @Test
    public void test_areColliding_shouldBeTrueIfColliding() {
        System.out.println(strPrintStart + "test_areColliding_shouldBeTrueIfColliding");

        AttackObject aMock = Mockito.mock(AttackObject.class);
        AttackObject bMock = Mockito.mock(AttackObject.class);

        when(aMock.getYPosition()).thenReturn(11);
        when(bMock.getYPosition()).thenReturn(19);

        assert(AttackCollisionUtility.areColliding(aMock, bMock, 10));

        System.out.println(strPrintFinish + "test_areColliding_shouldBeTrueIfColliding");
    }

    @Test
    public void test_areColliding_shouldBeFalseIfNotColliding() {
        System.out.println(strPrintStart + "test_areColliding_shouldBeFalseIfNotColliding");

        AttackObject aMock = Mockito.mock(AttackObject.class);
        AttackObject bMock = Mockito.mock(AttackObject.class);

        when(aMock.getYPosition()).thenReturn(500);
        when(bMock.getYPosition()).thenReturn(600);

        assertFalse(AttackCollisionUtility.areColliding(aMock, bMock, 3));

        System.out.println(strPrintFinish + "test_areColliding_shouldBeFalseIfNotColliding");
    }

    @Test
    public void test_checkCollisionWinner_WinnersWin() {
        System.out.println(strPrintStart + "test_checkCollisionWinner_WinnersWin");

        assert(AttackCollisionUtility.checkCollisionWinner(AttackObject.AttackType.Fire, AttackObject.AttackType.Water));
        assert(AttackCollisionUtility.checkCollisionWinner(AttackObject.AttackType.Ground, AttackObject.AttackType.Fire));
        assert(AttackCollisionUtility.checkCollisionWinner(AttackObject.AttackType.Water, AttackObject.AttackType.Ground));

        assertFalse(AttackCollisionUtility.checkCollisionWinner(AttackObject.AttackType.Water, AttackObject.AttackType.Fire));
        assertFalse(AttackCollisionUtility.checkCollisionWinner(AttackObject.AttackType.Fire, AttackObject.AttackType.Ground));
        assertFalse(AttackCollisionUtility.checkCollisionWinner(AttackObject.AttackType.Ground, AttackObject.AttackType.Water));

        assertFalse(AttackCollisionUtility.checkCollisionWinner(AttackObject.AttackType.Water, AttackObject.AttackType.Water));
        assertFalse(AttackCollisionUtility.checkCollisionWinner(AttackObject.AttackType.Fire, AttackObject.AttackType.Fire));
        assertFalse(AttackCollisionUtility.checkCollisionWinner(AttackObject.AttackType.Ground, AttackObject.AttackType.Ground));

        System.out.println(strPrintFinish + "test_checkCollisionWinner_WinnersWin");
    }

    @Test
    public void test_handleCollision_givesbeegPoint() {
        System.out.println(strPrintStart + "test_handleCollision_givesbeegPoint");

        AttackObject cMock = Mockito.mock(WaterAttack.class);
        AttackObject dMock = Mockito.mock(FireAttack.class);
        when(cMock.getType()).thenReturn(AttackObject.AttackType.Water);
        when(dMock.getType()).thenReturn(AttackObject.AttackType.Fire);
        PlayerController pMock = Mockito.mock(PlayerController.class);

        AttackCollisionUtility.handleCollision(cMock, dMock, pMock);
        Mockito.verify(pMock, Mockito.atLeastOnce()).addScore(AttackCollisionUtility.BIG_POINTS);

        System.out.println(strPrintFinish + "test_handleCollision_givesbeegPoint");
    }

    @Test
    public void test_handleCollision_givesmolPoint() {
        System.out.println(strPrintStart + "test_handleCollision_givesPoints");

        AttackObject cMock = Mockito.mock(GroundAttack.class);
        AttackObject dMock = Mockito.mock(GroundAttack.class);
        when(cMock.getType()).thenReturn(AttackObject.AttackType.Ground);
        when(dMock.getType()).thenReturn(AttackObject.AttackType.Ground);
        PlayerController pMock = Mockito.mock(PlayerController.class);

        AttackCollisionUtility.handleCollision(cMock, dMock, pMock);
        Mockito.verify(pMock, Mockito.atLeastOnce()).addScore(AttackCollisionUtility.SMALL_POINTS);

        System.out.println(strPrintFinish + "test_handleCollision_givesPoints");
    }

    @Test
    public void test_handleCollision_destroysLosers() {
        System.out.println(strPrintStart + "test_handleCollision_destroysLosers");

        AttackObject aMock = Mockito.mock(FireAttack.class);
        AttackObject bMock = Mockito.mock(FireAttack.class);
        AttackObject cMock = Mockito.mock(GroundAttack.class);
        AttackObject dMock = Mockito.mock(WaterAttack.class);

        when(aMock.getType()).thenReturn(AttackObject.AttackType.Fire);
        when(bMock.getType()).thenReturn(AttackObject.AttackType.Fire);
        when(cMock.getType()).thenReturn(AttackObject.AttackType.Ground);
        when(dMock.getType()).thenReturn(AttackObject.AttackType.Water);

        PlayerController pMock = Mockito.mock(PlayerController.class);

        AttackCollisionUtility.handleCollision(aMock, bMock, pMock);
        Mockito.verify(aMock, Mockito.atLeastOnce()).setDestroyed(true);
        Mockito.verify(bMock, Mockito.atLeastOnce()).setDestroyed(true);

        AttackCollisionUtility.handleCollision(dMock, cMock, pMock);
        Mockito.verify(cMock, Mockito.atLeastOnce()).setDestroyed(true);

        System.out.println(strPrintFinish + "test_handleCollision_destroysLosers");
    }

}
