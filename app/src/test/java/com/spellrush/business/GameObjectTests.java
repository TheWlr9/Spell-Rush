package com.spellrush.business;

import android.graphics.Canvas;

import com.spellrush.application.GameObject;

import junit.framework.TestCase;
import org.junit.Test;



public class GameObjectTests extends TestCase {

    private static final String strPrintStart = "\nStarting testPlayerController: ";
    private static final String strPrintFinish = "\nFinished testPlayerController: ";

    public class GameObjectMock extends GameObject{
        public GameObjectMock(int depth) {super(depth); }
        @Override
        public void update() { }
        @Override
        public void draw(Canvas canvas) { }
    }

    @Test
    public void test_compareTo_should_compareCorrectly() {
        System.out.println(strPrintStart + "test_compareTo_should_compareCorrectly");

        GameObjectMock o1 = new GameObjectMock(10);
        GameObjectMock o2 = new GameObjectMock(20);
        GameObjectMock o3 = new GameObjectMock(10);
        assertTrue( o1.compareTo(o2) < 0);
        assertTrue( o2.compareTo(o1) > 0);
        assertTrue( o1.compareTo(o1) == 0);
        assertTrue( o1.compareTo(o3) == 0);
        assertTrue( o3.compareTo(o1) == 0);

        System.out.println(strPrintFinish + "test_compareTo_should_compareCorrectly");
    }

    @Test
    public void test_compareTo_should_compareNegativesCorrectly() {
        System.out.println(strPrintStart + "test_compareTo_should_compareNegativesCorrectly");

        GameObjectMock o1 = new GameObjectMock(-20);
        GameObjectMock o2 = new GameObjectMock(-10);
        GameObjectMock o3 = new GameObjectMock(0);
        assertTrue( o1.compareTo(o2) < 0);
        assertTrue( o2.compareTo(o1) > 0);
        assertTrue( o1.compareTo(o1) == 0);
        assertTrue( o1.compareTo(o3) < 0);
        assertTrue( o3.compareTo(o1) > 0);

        System.out.println(strPrintFinish + "test_compareTo_should_compareNegativesCorrectly");
    }
}
