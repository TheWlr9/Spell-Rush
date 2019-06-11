package com.spellrush.presentation;

import com.spellrush.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createStartButton();
        createSettingsButton();
        createLeaderboardButton();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void createStartButton(){
        Button startButton = (Button) findViewById(R.id.startBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Start Button clicked", Toast.LENGTH_SHORT).show();
                Intent gameIntent = new Intent(HomeActivity.this, GameActivity.class);
                HomeActivity.this.startActivity(gameIntent);
            }
        });
    }

    private void createSettingsButton(){
        Button settingsButton = (Button) findViewById(R.id.settingsBtn);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Settings Button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createLeaderboardButton(){
        Button leaderBoardButton = (Button) findViewById(R.id.leaderBoardBtn);
        leaderBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent leaderboardIntent = new Intent(HomeActivity.this, LeaderboardActivity.class);
                HomeActivity.this.startActivity((leaderboardIntent));
            }
        });
    }
}
