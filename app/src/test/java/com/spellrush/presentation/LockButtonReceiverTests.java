package com.spellrush.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.spellrush.business.GameView;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

public class LockButtonReceiverTests extends TestCase {

    private static final String strPrintStart = "\nStarting LockButtonReceiverTest: ";
    private static final String strPrintFinish = "\nFinished LockButtonReceiverTest: ";

    @Test
    public void test_LockButtonReceiver_shouldSetPausedOnLock() {
        System.out.println(strPrintStart + "test_LockButtonReceiver_shouldSetPausedOnLock");

        GameView mockGame = Mockito.mock(GameView.class);
        Intent mockIntent = Mockito.mock(Intent.class);

        LockButtonReceiver sut = new LockButtonReceiver(Mockito.mock(Activity.class), mockGame);

        Mockito.when(mockIntent.getAction()).thenReturn(Intent.ACTION_SCREEN_OFF);
        sut.onReceive(Mockito.mock(Context.class),mockIntent);
        Mockito.verify(mockGame, Mockito.times(1)).setPaused(true);

        System.out.println(strPrintStart + "test_LockButtonReceiver_shouldSetPausedOnLock");
    }

    @Test
    public void test_LockButtonReceiver_shouldSetUnPausedOnUnLock() {
        System.out.println(strPrintStart + "test_LockButtonReceiver_shouldSetUnPausedOnUnLock");

        GameView mockGame = Mockito.mock(GameView.class);
        Intent mockIntent = Mockito.mock(Intent.class);

        LockButtonReceiver sut = new LockButtonReceiver(Mockito.mock(Activity.class), mockGame);

        Mockito.when(mockIntent.getAction()).thenReturn(Intent.ACTION_SCREEN_ON);
        sut.onReceive(Mockito.mock(Context.class),mockIntent);
        Mockito.verify(mockGame, Mockito.times(1)).setPaused(false);

        System.out.println(strPrintStart + "test_LockButtonReceiver_shouldSetUnPausedOnUnLock");
    }
}

