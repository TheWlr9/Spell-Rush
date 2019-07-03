package com.spellrush.objects;

import junit.framework.TestCase;

import org.junit.Test;

public class MediumEnemyAITests extends TestCase {


    private static final String strPrintStart = "\nStarting MediumEnemyAITests: ";
    private static final String strPrintFinish = "\nFinished MediumEnemyAITests: ";

    @Test
    public void test_update_shouldIncrementFrameCountAndReset() {
        System.out.println(strPrintStart + "test_update_shouldIncrementFrameCountAndReset");
        MediumEnemyAI ai = new MediumEnemyAI();
        int wait = ai.getWait();
        for(int i = 1; i < wait; i++){
            ai.update();
            assertTrue(ai.getFrameCount() == i);
        }

        ai.update();
        assertTrue(ai.getFrameCount() == 0);


        System.out.println(strPrintFinish + "test_update_shouldIncrementFrameCountAndReset");
    }

    @Test
    public void test_newWait_shouldGenerateWaitInBounds() {
        System.out.println(strPrintStart + "test_update_shouldGenerateNewWaitAfterTime");
        MediumEnemyAI ai = new MediumEnemyAI();
        ai.getWait();
        for(int i = 0 ; i < 50; i++) {
            int wait = ai.getWait();
            assertTrue(wait >= ai.MIN_WAIT && wait <= ai.MAX_WAIT);
        }
        System.out.println(strPrintFinish + "test_update_shouldGenerateNewWaitAfterTime");
    }
}

