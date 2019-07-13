package com.spellrush.business.spellRecognitionTests;

import com.spellrush.business.spellRecognition.FireRecognition;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.spellrush.business.ShapeRecognitionTests.*;

public final class FireRecognitionTests extends TestCase {
    private static final String strPrintStart = "\nStarting FireRecognitionTests: ";
    private static final String strPrintFinish = "\nFinished FireRecognitionTests: ";

    @Test
    public void test_draw_fire(){
        System.out.println(strPrintStart + "test_draw_fire");

        assertTrue(FireRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(FIRE_BOTRIGHT_PATH))));
        assertTrue(FireRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(FIRE_TOPRIGHT_PATH))));
        assertTrue(FireRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(FIRE_SPELL_ARRAY_PATH))));

        assertFalse(FireRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(WATER_SPELL_ARRAY_PATH))));
        assertFalse(FireRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(WATER_DOWNWARDS_PATH))));
        assertFalse(FireRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(GROUND_SPELL_ARRAY_PATH))));

        System.out.println(strPrintFinish + "test_draw_fire");
    }
}
