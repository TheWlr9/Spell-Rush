package com.spellrush.presentation;

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
import com.spellrush.objects.attacks.AttackCollisionUtility;
import com.spellrush.objects.attacks.AttackObject;
import com.spellrush.objects.attacks.FireAttack;
import com.spellrush.objects.attacks.GameBoard;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Queue;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static junit.framework.TestCase.assertTrue;

public class CombatSysTest {
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
    public void test_Collision() throws InterruptedException {

        GameBoard theBoard = LevelManager.getInstance().getGameBoard();
         ArrayList<AttackObject> attacks = theBoard.getAttacks();

        theBoard.clear(); //Reset the game board
        while(attacks.size()>0);//wait for the board to clear
        while(attacks.size()<1){
            attacks = theBoard.getAttacks();
        }
        onView(ViewMatchers.withId(R.id.surfaceView)).perform(attemptSpell(ShapeRecognitionTests.GROUND_SPELL_ARRAY_PATH));

        boolean aCollision= false;
        AttackObject attackA = attacks.get(0);
        AttackObject attackB = attacks.get(1);

        while( !attackA.reachedEnd(theBoard) && !aCollision) {
            if(attackA.wasDestroyed() || attackB.wasDestroyed() ){
                aCollision=true;
                System.out.println("THERE WAS A COLLISION");
            }
        }
        assertTrue(aCollision);

    }
}
