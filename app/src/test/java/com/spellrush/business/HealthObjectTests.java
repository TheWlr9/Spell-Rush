package com.spellrush.business;

import android.graphics.Canvas;

import com.spellrush.objects.HealthObject;

import junit.framework.TestCase;

import org.junit.Test;

public class HealthObjectTests extends TestCase {
    public static final int MAXHP = 100;

    public class stubObject extends HealthObject{
        public stubObject(){
            super(0, MAXHP);
        }

        @Override
        protected void onDestroyed() {
            return;
        }

        @Override
        public void update() {
            return;
        }

        @Override
        public void draw(Canvas canvas) {
            return;
        }
    }

    private HealthObject testObject;
    private static final String strPrintStart = "\nStarting testObjectController: ";
    private static final String strPrintFinish = "\nFinished testObjectController: ";

    public HealthObjectTests(String arg0){
        super(arg0);
        testObject = new stubObject();
    }

    @Test
    public void test_hp_should_initializeToMaxHP() {
        System.out.println(strPrintStart + "test_hp_should_initializeToMaxHP");

        testObject = new stubObject();
        assertEquals(MAXHP, testObject.getHP());

        System.out.println(strPrintFinish + "test_hp_should_initializeToMaxHP");
    }

    @Test
    public void test_getMaxHP_shouldGetMaxHP() {
        System.out.println(strPrintStart + "test_getMaxHP_shouldGetMaxHP");

        assertEquals(MAXHP, testObject.getMaxHP());

        System.out.println(strPrintFinish + "test_getMaxHP_shouldGetMaxHP");
    }

    @Test
    public void test_addHp_should_notExceedMaxHP() {
        System.out.println(strPrintStart + "addHp_should_notExceedMaxHP");

        testObject.addHP(MAXHP);
        assertEquals(MAXHP, testObject.getHP());

        testObject.addHP(999999999);
        assertEquals(MAXHP, testObject.getHP());

        System.out.println(strPrintFinish + "addHp_should_notExceedMaxHP");
    }

    @Test
    public void test_addHp_should_notAddNegativeHp() {
        System.out.println(strPrintStart + "addHp_should_notAddNegativeHp");

        int before = testObject.getHP();
        testObject.addHP(-5);
        assertEquals(testObject.getHP(), before);

        System.out.println(strPrintFinish + "addHp_should_notAddNegativeHp");
    }

    @Test
    public void test_getHit_should_notLoseNegativeHp() {
        System.out.println(strPrintStart + "getHit_should_notLoseNegativeHp");

        int before = testObject.getHP();
        testObject.getHit(-5);
        assertEquals(testObject.getHP(), before);

        System.out.println(strPrintFinish + "oseHp_should_notLoseNegativeHp");
    }

    @Test
    public void test_getHit_should_notGoLowerThanZero() {
        System.out.println(strPrintStart + "getHit_should_notGoLowerThanZero");

        testObject.getHit(MAXHP);
        assertEquals(0, testObject.getHP());

        testObject.getHit(999999999);
        assertEquals(0, testObject.getHP());

        System.out.println(strPrintFinish + "getHit_should_notGoLowerThanZero");
    }

    @Test
    public void test_getHit_should_decrementHP() {
        System.out.println(strPrintStart + "getHit_should_decrementHP");

        // Add HP first
        testObject.addHP(5);

        int before = testObject.getHP();
        testObject.getHit(5);
        assertEquals(testObject.getHP(), before - 5);

        System.out.println(strPrintFinish + "getHit_should_decrementHP");
    }

    @Test
    public void test_addHp_should_incrementHP(){
        System.out.println(strPrintStart + "addHp_should_incrementHP");

        // Lose HP first
        testObject.getHit(5);

        int before = testObject.getHP();
        testObject.addHP(5);
        assertEquals(testObject.getHP(), before + 5);

        System.out.println(strPrintFinish + "addHp_should_incrementHP");
    }
}
