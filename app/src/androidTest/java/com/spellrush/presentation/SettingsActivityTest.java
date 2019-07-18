package com.spellrush.presentation;

import android.view.View;
import android.widget.SeekBar;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.spellrush.R;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class SettingsActivityTest {

    @Rule
    public ActivityTestRule<SettingsActivity> mActivityTestRule = new ActivityTestRule<>(SettingsActivity.class);

    @Test
    public void settingsActivityTest() {
        onView(ViewMatchers.withId(R.id.masterVolumeSlider)).check(matches(isDisplayed()));
        onView(withId(R.id.masterVolumeSlider)).perform(setProgress(0));
        onView(withId(R.id.masterVolumeSlider)).perform(setProgress(15));
        onView(ViewMatchers.withId(R.id.soundEffectsVolumeSlider)).check(matches(isDisplayed()));
        onView(withId(R.id.soundEffectsVolumeSlider)).perform(setProgress(0));
        onView(withId(R.id.soundEffectsVolumeSlider)).perform(setProgress(15));
    }

    public static ViewAction setProgress(final int progress) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress(progress);
            }
            @Override
            public String getDescription() {
                return "Set a progress on a SeekBar";
            }
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }
        };
    }
}
