package com.spellrush.business.spellRecognitionTests;

import com.spellrush.business.spellRecognition.GroundRecognition;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.spellrush.business.ShapeRecognitionTests.*;

public final class GroundRecognitionTests extends TestCase {
    private static final String strPrintStart = "\nStarting GroundRecognitionTests: ";
    private static final String strPrintFinish = "\nFinished GroundRecognitionTests: ";

    @Test
    public void test_draw_ground(){
        System.out.println(strPrintStart + "test_draw_ground");

        assertTrue(GroundRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(GROUND_SPELL_ARRAY_PATH))));

        assertFalse(GroundRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(FIRE_SPELL_ARRAY_PATH))));
        assertFalse(GroundRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(FIRE_BOTRIGHT_PATH))));
        assertFalse(GroundRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(FIRE_TOPRIGHT_PATH))));
        assertFalse(GroundRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(WATER_SPELL_ARRAY_PATH))));
        assertFalse(GroundRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(WATER_DOWNWARDS_PATH))));

        System.out.println(strPrintFinish + "test_draw_ground");
    }
}
