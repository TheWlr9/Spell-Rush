package com.spellrush.presentation.UI;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.MotionEvents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;

import com.spellrush.R;
import com.spellrush.business.LevelManager.LevelManager;
import com.spellrush.business.ShapeRecognitionTests;
import com.spellrush.objects.attacks.AttackObject;
import com.spellrush.objects.attacks.FireAttack;
import com.spellrush.objects.attacks.GameBoard;
import com.spellrush.objects.attacks.GroundAttack;
import com.spellrush.objects.attacks.WaterAttack;
import com.spellrush.presentation.GameActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Queue;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

public class FingerPathLayerTest {
    private static final int MILLIS_BETWEEN_POINTS = 10;

    @Rule
    public IntentsTestRule<GameActivity> gameActivityTestRule = new IntentsTestRule(GameActivity.class);

    private static ViewAction attemptSpell(final PointF[] spellPath) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayed();
            }

            @Override
            public String getDescription() {
                return "Send touch events.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                // Get view absolute position
                int[] location = new int[2];
                view.getLocationOnScreen(location);

                // Offset coordinates by view position
                float[] coordinates = new float[] { spellPath[0].x + location[0], spellPath[0].y + location[1] };
                float[] precision = new float[] { 1f, 1f };

                // Send down event
                MotionEvent down = MotionEvents.sendDown(uiController, coordinates, precision).down;
                for(int i = 1; i < spellPath.length; i++) {
                    uiController.loopMainThreadForAtLeast(MILLIS_BETWEEN_POINTS);

                    coordinates = new float[] {spellPath[i].x + location[0], spellPath[i].y + location[1]};

                    // Move the down event
                    MotionEvents.sendMovement(uiController, down, coordinates);
                }

                // Send up event
                MotionEvents.sendUp(uiController, down, coordinates);

                //Janky way of doing this, but the issue is with adding the attack to the actual data containers
                //in GameBoard... Takes some time. There might be a way to make this less "jank",
                //but in the limited amount of time we have, this might have to cut it...
                uiController.loopMainThreadForAtLeast(MILLIS_BETWEEN_POINTS * 30);
            }
        };
    }

    @Test
    public void test_fire_spell(){
        //NOTE The dependencies here... Are they ok because we're testing them so we need to know how they work?
        GameBoard theBoard = LevelManager.getInstance().getGameBoard();
        Queue<AttackObject> attacksToAdd = theBoard.getAttacksToAdd();
        ArrayList<AttackObject> attacks = theBoard.getAttacks();

        theBoard.clear(); //Reset the game board

        onView(ViewMatchers.withId(R.id.surfaceView)).perform(attemptSpell(ShapeRecognitionTests.FIRE_SPELL_ARRAY_PATH));

        boolean hasCreated = false;
        int initialAttacksToAddSize = attacksToAdd.size(); //Value

        //Need to go through the whole queue because of the polling and adding in the same order.
        //This loop currently will go n iterations.
        for(int i = 0; i < initialAttacksToAddSize; i++){
            AttackObject curr = attacksToAdd.poll();

            if(curr != null && curr.isPlayerAttack() && curr.getClass() == FireAttack.class){
                hasCreated = true;
            }

            attacksToAdd.add(curr);
        }
        for(int i = 0; i < attacks.size() && !hasCreated; i++){
            AttackObject curr = attacks.get(i);

            if(curr != null && curr.isPlayerAttack() && curr.getClass() == FireAttack.class){
                hasCreated = true;
            }
        }

        if(!hasCreated) {
            throw new AssertionError("Error on recognizing and creating fire spell");
        }
    }

    @Test
    public void test_water_spell(){
        //NOTE The dependencies here... Are they ok because we're testing them so we need to know how they work?
        GameBoard theBoard = LevelManager.getInstance().getGameBoard();
        Queue<AttackObject> attacksToAdd = theBoard.getAttacksToAdd();
        ArrayList<AttackObject> attacks = theBoard.getAttacks();

        theBoard.clear(); //Reset the game board

        onView(ViewMatchers.withId(R.id.surfaceView)).perform(attemptSpell(ShapeRecognitionTests.WATER_SPELL_ARRAY_PATH));

        boolean hasCreated = false;
        int initialAttacksToAddSize = attacksToAdd.size(); //Value

        //Need to go through the whole queue because of the polling and adding in the same order.
        //This loop currently will go n iterations.
        for(int i = 0; i < initialAttacksToAddSize; i++){
            AttackObject curr = attacksToAdd.poll();

            if(curr != null && curr.isPlayerAttack() && curr.getClass() == WaterAttack.class){
                hasCreated = true;
            }

            attacksToAdd.add(curr);
        }
        for(int i = 0; i < attacks.size() && !hasCreated; i++){
            AttackObject curr = attacks.get(i);

            if(curr != null && curr.isPlayerAttack() && curr.getClass() == WaterAttack.class){
                hasCreated = true;
            }
        }

        if(!hasCreated) {
            throw new AssertionError("Error on recognizing and creating water spell");
        }
    }

    @Test
    public void test_ground_Spell(){
        //NOTE The dependencies here... Are they ok because we're testing them so we need to know how they work?
        GameBoard theBoard = LevelManager.getInstance().getGameBoard();
        Queue<AttackObject> attacksToAdd = theBoard.getAttacksToAdd();
        ArrayList<AttackObject> attacks = theBoard.getAttacks();

        theBoard.clear(); //Reset the game board

        onView(ViewMatchers.withId(R.id.surfaceView)).perform(attemptSpell(ShapeRecognitionTests.GROUND_SPELL_ARRAY_PATH));

        boolean hasCreated = false;
        int initialAttacksToAddSize = attacksToAdd.size(); //Value

        //Need to go through the whole queue because of the polling and adding in the same order.
        //This loop currently will go n iterations.
        for(int i = 0; i < initialAttacksToAddSize; i++){
            AttackObject curr = attacksToAdd.poll();

            if(curr != null && curr.isPlayerAttack() && curr.getClass() == GroundAttack.class){
                hasCreated = true;
            }

            attacksToAdd.add(curr);
        }
        for(int i = 0; i < attacks.size() && !hasCreated; i++){
            AttackObject curr = attacks.get(i);

            if(curr != null && curr.isPlayerAttack() && curr.getClass() == GroundAttack.class){
                hasCreated = true;
            }
        }

        if(!hasCreated) {
            throw new AssertionError("Error on recognizing and creating ground spell");
        }
    }

}
