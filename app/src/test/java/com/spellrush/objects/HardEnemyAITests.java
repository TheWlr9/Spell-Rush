package com.spellrush.objects;

import junit.framework.TestCase;

import org.junit.Test;

public class HardEnemyAITests  extends TestCase {


        private static final String strPrintStart = "\nStarting HardEnemyAITests: ";
        private static final String strPrintFinish = "\nFinished HardEnemyAITests: ";

        @Test
        public void test_update_shouldIncrementFrameCountAndReset() {
            System.out.println(strPrintStart + "test_update_shouldIncrementFrameCountAndReset");
            HardEnemyAI ai = new HardEnemyAI();
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
            System.out.println(strPrintStart + "test_update_shouldGenerateNewWaitWithinBounds");
            HardEnemyAI ai = new HardEnemyAI();
            ai.getWait();
            for(int i = 0 ; i < 50; i++) {
                int wait = ai.getWait();
                assertTrue((wait >= ai.MIN_WAIT && wait <= ai.MAX_WAIT)||(wait==ai.MULTI_SHOT_WAIT));
                for(int j = 0; j<wait;j++){
                    ai.update();
                }
            }
            System.out.println(strPrintFinish + "test_update_shouldGenerateNewWaitWithinBounds");
        }
    }