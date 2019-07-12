package com.spellrush.presentation;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.spellrush.business.GameView;

public class LockButtonReceiver extends BroadcastReceiver {
    private Activity activity = null;
    private GameView theView = null;

    public LockButtonReceiver(Activity activity, GameView theView) {
        this.activity = activity;
        this.theView = theView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            theView.setPaused(true);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            theView.setPaused(false);
        }
    }
}