package com.spellrush.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.spellrush.R;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        createMenuButton();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        // Ensure it does nothing...
    }

    private void createMenuButton(){
        Button startButton = (Button) findViewById(R.id.menuBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(GameActivity.this, HomeActivity.class);
                GameActivity.this.startActivity(menuIntent);
            }
        });
    }
}
