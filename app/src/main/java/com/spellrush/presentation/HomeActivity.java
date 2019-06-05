package com.spellrush.presentation;

import android.app.Activity;
import android.os.Bundle;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameUIView(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
