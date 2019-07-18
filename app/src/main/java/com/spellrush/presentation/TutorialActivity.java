package com.spellrush.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.spellrush.R;

public class TutorialActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ViewPager viewPager = (ViewPager) findViewById(R.id.tutorial);
        TutorialAdapter adapter= new TutorialAdapter(this);
        viewPager.setAdapter(adapter);

    }

}
