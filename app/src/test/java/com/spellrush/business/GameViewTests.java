package com.spellrush.business;

import android.content.Context;
import android.graphics.Canvas;

import com.spellrush.business.TestDoubles.FakeContext;
import com.spellrush.objects.GameObject;
import com.spellrush.presentation.UI.FingerPathLayer;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameViewTests extends TestCase {

    // Fake shapeAI and FingerPathLayer for update method
    class fakeFPLayer extends FingerPathLayer{
        fakeFPLayer(){
            super(true);
        }
    }
    class fakeShapeAI extends ShapeRecognition{
        public fakeShapeAI() {
            super(new fakeFPLayer());
        }
    }

    // GameObject to mock GameView within GameThread
    class MockObj extends GameObject {
        public boolean updated;
        public boolean drawn;

        public MockObj(int d) {
            super(d);
            updated = false;
            drawn = false;
        }

        @Override
        public void update() {
            updated = true;
        }

        @Override
        public void draw(Canvas canvas) {
            drawn = true;
        }
    }

    GameView view;

    Queue<GameObject> addObjects;
    Queue<GameObject> rmvObjects;
    ArrayList<GameObject> currObjects;
    FakeContext fakeContext;

    private static final String strPrintStart = "\nStarting GameViewTests: ";
    private static final String strPrintFinish = "\nFinished GameViewTests: ";

    public void setUp() {
        addObjects = new LinkedList<>();
        rmvObjects = new LinkedList<>();
        currObjects = new ArrayList<>();
        fakeContext = new FakeContext();
        view = new GameView(fakeContext, currObjects, addObjects, rmvObjects, new fakeShapeAI());
    }

    @Test
    public void test_GameView_getInstance_shouldGetInstance() {
        System.out.println(strPrintStart + "test_GameView_getInstance_shouldGetInstance");
        setUp();
        GameView inst = GameView.getInstance();
        assertNotNull(inst);
        assertEquals(inst, view);
        System.out.println(strPrintFinish + "test_GameView_getInstance_shouldGetInstance");
    }

    @Test
    public void test_GameView_addObject_shouldAddToObjectsToAdd() {
        System.out.println(strPrintStart + "test_GameView_addObject_shouldAddToObjectsToAdd");
        setUp();

        MockObj o1 = new MockObj(1);
        GameView.addObject(o1);
        MockObj o2 = new MockObj(-1);
        GameView.addObject(o2);
        MockObj o3 = new MockObj(9999);
        GameView.addObject(o3);

        assertTrue(addObjects.contains(o1));
        assertTrue(addObjects.contains(o2));
        assertTrue(addObjects.contains(o3));

        System.out.println(strPrintFinish + "test_GameView_addObject_shouldAddToObjectsToAdd");
    }

    @Test
    public void test_GameView_removeObject_shouldAddToObjectsToDelete() {
        System.out.println(strPrintStart + "test_GameView_removeObject_shouldAddToObjectsToDelete");
        setUp();

        MockObj o1 = new MockObj(1);
        GameView.removeObject(o1);
        MockObj o2 = new MockObj(-1);
        GameView.removeObject(o2);
        MockObj o3 = new MockObj(9999);
        GameView.removeObject(o3);

        assertTrue(rmvObjects.contains(o1));
        assertTrue(rmvObjects.contains(o2));
        assertTrue(rmvObjects.contains(o3));

        System.out.println(strPrintFinish + "test_GameView_removeObject_shouldAddToObjectsToDelete");
    }

    @Test
    public void test_GameView_removeObject_shouldRemoveObjectsAfterUpdate() {
        System.out.println(strPrintStart + "test_GameView_removeObject_shouldRemoveObjectsAfterUpdate");

        setUp();

        // ARRANGE
        MockObj o1 = new MockObj(-1);
        MockObj o2 = new MockObj(10);
        MockObj o3 = new MockObj(999);

        currObjects = new ArrayList<GameObject>();
        currObjects.add(o1);
        currObjects.add(o2);
        currObjects.add(o3);

        view = new GameView(fakeContext, currObjects, addObjects, rmvObjects, new fakeShapeAI());

        // TEST
        view.removeObject(o1);
        view.removeObject(o2);
        view.removeObject(o3);

        view.update();

        assertTrue(currObjects.isEmpty());
        assertTrue(rmvObjects.isEmpty());

        System.out.println(strPrintFinish + "test_GameView_removeObject_shouldRemoveObjectsAfterUpdate");
    }

    @Test
    public void test_GameView_addObject_shouldAddObjectsAfterUpdate() {
        System.out.println(strPrintStart + "test_GameView_removeObject_shouldAddObjectsAfterUpdate");

        setUp();

        MockObj o2 = new MockObj(-1);
        view.addObject(o2);
        MockObj o3 = new MockObj(9999);
        view.addObject(o3);
        MockObj o1 = new MockObj(5);
        view.addObject(o1);

        view.update();

        assertTrue(currObjects.contains(o1));
        assertTrue(currObjects.contains(o2));
        assertTrue(currObjects.contains(o3));
        assertTrue(addObjects.isEmpty());

        System.out.println(strPrintFinish + "test_GameView_removeObject_shouldAddObjectsAfterUpdate");
    }

    @Test
    public void test_GameView_update_shouldUpdateObjects() {
        System.out.println(strPrintStart + "test_GameView_update_shouldUpdateObjects");
        setUp();

        MockObj o1 = new MockObj(5);
        MockObj o2 = new MockObj(0);
        assertFalse(o1.updated);
        view.addObject(o1);
        view.addObject(o2);
        view.update();
        view.update();
        assertTrue(o1.updated);
        assertTrue(o2.updated);

        System.out.println(strPrintFinish + "test_GameView_update_shouldUpdateObjects");
    }

    @Test
    public void test_GameView_update_shouldNotUpdateObjectsOnPaused() {
        System.out.println(strPrintStart + "test_GameView_update_shouldNotUpdateObjectsOnPaused");

        ArrayList<GameObject> fakeList = Mockito.mock(ArrayList.class);
        Queue<GameObject> fakeQueue = Mockito.mock(Queue.class);
        GameView newGameView = new GameView(Mockito.mock(Context.class), fakeList, fakeQueue, fakeQueue, Mockito.mock(ShapeRecognition.class));
        GameObject mockObj = Mockito.mock(GameObject.class);

        newGameView.setPaused(true);

        newGameView.addObject(mockObj);
        newGameView.update();
        newGameView.update();

        Mockito.verify(mockObj, Mockito.never()).update();

        System.out.println(strPrintFinish + "test_GameView_update_shouldNotUpdateObjectsOnPaused");
    }

}