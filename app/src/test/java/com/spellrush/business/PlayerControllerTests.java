package com.spellrush.business;

import com.spellrush.business.TestDoubles.FakeContext;
import com.spellrush.objects.GameObject;
import com.spellrush.presentation.UI.FingerPathLayer;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class PlayerControllerTests extends TestCase {


    // Fake shapeAI and FingerPathLayer for Mock GameView
    class fakeFPLayer extends FingerPathLayer {
        fakeFPLayer(){
            super(true);
        }
    }
    class fakeShapeAI extends ShapeRecognition{
        public fakeShapeAI() {
            super(new fakeFPLayer());
        }
    }
    class MockGameView extends GameView{
        private boolean trig;
        MockGameView(){
            super(new FakeContext(), new ArrayList<GameObject>(), new LinkedList<GameObject>(), new LinkedList<GameObject>(), new fakeShapeAI() );
            trig = false;
        }

        @Override
        public void triggerGameOver() {
            trig = true;
        }
    }

    private PlayerController testPlayer;
    private static final String strPrintStart = "\nStarting testPlayerController: ";
    private static final String strPrintFinish = "\nFinished testPlayerController: ";

    public PlayerControllerTests(String arg0){
        super(arg0);
        testPlayer = PlayerController.getInstance();
    }

    @Test
    public void test_addScore_should_incrementScore() {
        System.out.println(strPrintStart + "addScore_should_incrementScore");

        int before = testPlayer.getScore();
        testPlayer.addScore(5);
        assertEquals(testPlayer.getScore(), before + 5);

        System.out.println(strPrintFinish + "addScore_should_incrementScore");
    }

    @Test
    public void test_reset_should_resetScoreAndHealth() {
        System.out.println(strPrintStart + "test_reset_should_resetScoreAndHealth");

        testPlayer.reset();
        assertEquals(testPlayer.getScore(), 0);
        assertEquals(testPlayer.getHP(), testPlayer.getMaxHP());

        System.out.println(strPrintFinish + "test_reset_should_resetScoreAndHealth");
    }

    @Test
    public void test_onDestroyed_should_triggerGameView() {
        System.out.println(strPrintStart + "test_onDestroyed_should_triggerGameView");

        // setup
        MockGameView mockView = new MockGameView();
        assertFalse(mockView.trig);

        // test
        testPlayer.onDestroyed();
        assertTrue(mockView.trig);

        System.out.println(strPrintFinish + "test_onDestroyed_should_triggerGameView");
    }

}
