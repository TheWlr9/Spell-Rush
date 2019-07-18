package com.spellrush.business.spellRecognitionTests;

import com.spellrush.business.spellRecognition.WaterRecognition;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.spellrush.business.ShapeRecognitionTests.*;

public final class WaterRecognitionTests extends TestCase {
    private static final String strPrintStart = "\nStarting WaterRecognitionTests: ";
    private static final String strPrintFinish = "\nFinished WaterRecognitionTests: ";

    @Test
    public void test_draw_water(){
        System.out.println(strPrintStart + "test_draw_water");

        assertTrue(WaterRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(WATER_SPELL_ARRAY_PATH))));
        assertTrue(WaterRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(WATER_DOWNWARDS_PATH))));

        assertFalse(WaterRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(FIRE_SPELL_ARRAY_PATH))));
        assertFalse(WaterRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(FIRE_BOTRIGHT_PATH))));
        assertFalse(WaterRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(FIRE_TOPRIGHT_PATH))));
        assertFalse(WaterRecognition.hasDrawnWith(new ArrayList<>(Arrays.asList(GROUND_SPELL_ARRAY_PATH))));

        System.out.println(strPrintFinish + "test_draw_water");
    }
}
