package com.spellrush.business;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.spellrush.business.TestDoubles.FakeSH;
import com.spellrush.objects.IGameObject;

import junit.framework.TestCase;

import org.junit.Test;

public class GameThreadTests extends TestCase {

    class ErrorFakeSH extends FakeSH {
        @Override
        public Canvas lockCanvas() {
            throw new RuntimeException();
        }
    }

    // GameObject to mock GameView within GameThread
    class MockObj implements IGameObject{
        public boolean updated;
        public boolean drawn;

        public MockObj() {updated = false; drawn = false;}

        @Override
        public void update() {
            updated=true;
        }

        @Override
        public void draw(Canvas canvas) {
            drawn=true;
        }
    }

    MockObj gameObj;
    SurfaceHolder fakeSH;
    GameThread thread;

    private static final String strPrintStart = "\nStarting GameThreadTests: ";
    private static final String strPrintFinish = "\nFinished GameThreadTests: ";

    public void setUp() {
        gameObj = new MockObj();
        fakeSH = new FakeSH();
        thread = new GameThread(fakeSH, gameObj);
    }

    public void setUpErrorSH() {
        gameObj = new MockObj();
        fakeSH = new ErrorFakeSH();
        thread = new GameThread(fakeSH, gameObj);
    }

    @Test
    public void test_GameThread_setRunning_shouldSetIsRunning() {
        System.out.println(strPrintStart + "test_GameThread_setRunning_shouldSetIsRunning");

        setUp();
        assertFalse(thread.getRunning());
        thread.setRunning(true);
        assertTrue(thread.getRunning());
        thread.setRunning(false);
        assertFalse(thread.getRunning());

        System.out.println(strPrintFinish + "test_GameThread_setRunning_shouldSetIsRunning");
    }

    @Test
    public void test_GameThread_Update_shouldCallUpdateOnGameView() {
        System.out.println(strPrintStart + "test_GameThread_Update_shouldCallUpdateOnGameView");
        setUp();

        thread.runGameFrame();
        assertTrue(gameObj.updated);

        System.out.println(strPrintFinish + "test_GameThread_Update_shouldCallUpdateOnGameView");
    }

    @Test
    public void test_GameThread_Update_shouldCallDrawOnGameView() {
        System.out.println(strPrintStart + "test_GameThread_Update_shouldCallDrawOnGameView");
        setUp();

        thread.runGameFrame();
        assertTrue(gameObj.drawn);

        System.out.println(strPrintFinish + "test_GameThread_Update_shouldCallDrawOnGameView");
    }

    @Test
    public void test_GameThread_Update_shouldNotUpdateOnSHError() {
        System.out.println(strPrintStart + "test_GameThread_Update_shouldNotUpdateOnSHError");
        setUpErrorSH();

        thread.runGameFrame();
        assertFalse(gameObj.updated);

        System.out.println(strPrintFinish + "test_GameThread_Update_shouldNotUpdateOnSHError");
    }

    @Test
    public void test_GameThread_Update_shouldNotDrawOnSHError() {
        System.out.println(strPrintStart + "test_GameThread_Update_shouldNotDrawOnSHError");
        setUpErrorSH();

        thread.runGameFrame();
        assertFalse(gameObj.drawn);

        System.out.println(strPrintFinish + "test_GameThread_Update_shouldNotDrawOnSHError");
    }

}