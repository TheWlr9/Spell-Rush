package com.spellrush.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.spellrush.application.Services;
import com.spellrush.buisness.LeaderboardController;
import com.spellrush.objects.ScoreEntry;

import java.util.List;
import com.spellrush.R;

public class LeaderboardActivity extends Activity {
    private final int MAX_NUM_OF_ROWS = 10;
    private LeaderboardController leaderboardController;
    private List<ScoreEntry> scoreEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //shows the leaderboard
        setContentView(R.layout.activity_leaderboard);

        //initialize the leaderboard controller
        leaderboardController = new LeaderboardController(Services.getLeaderboardPersistence());

        try{
            // get scores from the controller
            scoreEntries = leaderboardController.getAllScoresSorted();
            String currName, currScore;
            ScoreEntry currScoreEntry;
            TableRow currRow;
            TableLayout scoresTable = (TableLayout)findViewById(R.id.leaderboard_table);

            int maxRows = (scoreEntries.size() < MAX_NUM_OF_ROWS) ? scoreEntries.size() : MAX_NUM_OF_ROWS;

            for(int i = 0; i < maxRows; i++){
                currScoreEntry = scoreEntries.get(i);
                currName = currScoreEntry.getPlayerName();
                currScore = String.format("%d", currScoreEntry.getPlayerScore());
                currRow = populateRow(currName, currScore);
                scoresTable.addView(currRow);
            }
        }

        catch(Exception e){
            displayException(e);
        }
    } //end onCreate()

    // populates each cell in the row
    private TableRow populateRow(String name, String score) {
        TableRow newRow = new TableRow(this);

        // create textView for the name in this row
        TextView rowName = new TextView(this);
        rowName.setText(name);
        rowName.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

        // create textView for the score in this row
        TextView rowScore = new TextView(this);
        rowScore.setText(score);
        rowScore.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

        // adds the textview of name and score into the row
        newRow.addView(rowName);
        newRow.addView(rowScore);
        newRow.setLayoutParams(new ViewGroup.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        newRow.setPadding(10,10,10,10);
        return newRow;
    } // end populateRow()

    private void displayException(Exception e) {
        AlertDialog.Builder message = new AlertDialog.Builder(this);
        message.setTitle("Leaderboard exception");
        message.setMessage(e.getMessage());
        message.setCancelable(false);
        message.setNeutralButton("Made an oopsie", null);
        message.show();
    } //end displayException(e)
}
