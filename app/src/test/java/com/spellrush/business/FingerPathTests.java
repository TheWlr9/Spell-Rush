package com.spellrush.business;

import android.view.MotionEvent;

import com.spellrush.presentation.UI.FingerPathLayer;

import junit.framework.TestCase;

import org.junit.Test;

public class FingerPathTests extends TestCase {
    private static final String strPrintStart = "\nStarting FingerPathTests: ";
    private static final String strPrintFinish = "\nFinished FingerPathTests: ";

    @Test
    public void test_NotNull_should_setupFingerPathSuccessfully(){
        System.out.println(strPrintStart + "test_NotNull_should_setupFingerPathSuccessfully");

        FingerPathLayer fpl = new FingerPathLayer(true);

        assertNotNull(fpl.getPath());
        /*
        fpl.onTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, 0, 0, 0));
        fpl.onTouchEvent(MotionEvent.obtain(10, 15, MotionEvent.ACTION_MOVE, 15, 10, 0));
        fpl.onTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0));
        assertFalse(fpl.getPath().isEmpty());
        */

        System.out.println(strPrintFinish + "test_NotNull_should_setupFingerPathSuccessfully");
    }
}
