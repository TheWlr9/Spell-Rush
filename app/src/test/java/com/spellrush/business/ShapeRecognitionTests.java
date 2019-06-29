package com.spellrush.business;

import android.graphics.PointF;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ShapeRecognitionTests extends TestCase {
    private static final String strPrintStart = "\nStarting ShapeRecognitionTests: ";
    private static final String strPrintFinish = "\nFinished ShapeRecognitionTests: ";

    public static class StubPointF extends PointF {
        StubPointF(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    final static PointF[] GROUND_SPELL_ARRAY_PATH = {new StubPointF(273, 1314),
            new StubPointF(283, 1289), new StubPointF(313, 1244), new StubPointF(335, 1212),
            new StubPointF(380, 1170), new StubPointF(418, 1143), new StubPointF(468, 1115),
            new StubPointF(489, 1105), new StubPointF(547, 1086), new StubPointF(603, 1076),
            new StubPointF(653, 1078), new StubPointF(685, 1086), new StubPointF(730, 1101),
            new StubPointF(762, 1122), new StubPointF(779, 1135), new StubPointF(806, 1166),
            new StubPointF(837, 1210), new StubPointF(856, 1241), new StubPointF(880, 1281),
            new StubPointF(890, 1296)};

    final static PointF[] FIRE_SPELL_ARRAY_PATH = {new StubPointF(263, 1082),
            new StubPointF(267, 1026), new StubPointF(284, 957), new StubPointF(312, 882),
            new StubPointF(333, 836), new StubPointF(366, 790), new StubPointF(406, 754),
            new StubPointF(416, 749), new StubPointF(420, 755), new StubPointF(423, 790),
            new StubPointF(420, 848), new StubPointF(416, 886), new StubPointF(417, 906),
            new StubPointF(423, 915), new StubPointF(442, 904), new StubPointF(497, 848),
            new StubPointF(553, 783), new StubPointF(605, 730), new StubPointF(637, 705),
            new StubPointF(667, 697), new StubPointF(678, 712), new StubPointF(683, 777),
            new StubPointF(673, 853), new StubPointF(655, 939), new StubPointF(643, 999),
            new StubPointF(641, 1014), new StubPointF(645, 1018), new StubPointF(680, 998),
            new StubPointF(745, 957), new StubPointF(807, 923), new StubPointF(853, 914),
            new StubPointF(870, 927), new StubPointF(879, 959), new StubPointF(863, 1061),
            new StubPointF(781, 1287), new StubPointF(726, 1377), new StubPointF(633, 1490),
            new StubPointF(571, 1539), new StubPointF(501, 1578), new StubPointF(467, 1593),
            new StubPointF(396, 1600), new StubPointF(334, 1575), new StubPointF(291, 1515),
            new StubPointF(269, 1441), new StubPointF(264, 1363), new StubPointF(273, 1300)};

    final static PointF[] WATER_SPELL_ARRAY_PATH = {new StubPointF(189, 1050),
            new StubPointF(204, 1026), new StubPointF(237, 980), new StubPointF(257, 954),
            new StubPointF(291, 921), new StubPointF(316, 904), new StubPointF(333, 898),
            new StubPointF(347, 896), new StubPointF(359, 902), new StubPointF(372, 915),
            new StubPointF(381, 927), new StubPointF(404, 955), new StubPointF(444, 990),
            new StubPointF(479, 1007), new StubPointF(523, 1009), new StubPointF(567, 1002),
            new StubPointF(599, 986), new StubPointF(612, 976), new StubPointF(649, 933),
            new StubPointF(672, 910), new StubPointF(693, 901), new StubPointF(712, 905),
            new StubPointF(736, 924), new StubPointF(767, 963), new StubPointF(772, 971),
            new StubPointF(802, 1001), new StubPointF(827, 1005), new StubPointF(864, 971),
            new StubPointF(918, 888), new StubPointF(945, 850), new StubPointF(956, 843),
            new StubPointF(963, 844), new StubPointF(1001, 873), new StubPointF(1051, 921),
            new StubPointF(1065, 930)};

    @Test
    public void test_draw_water(){
        System.out.println(strPrintStart + "test_draw_water");

        assertTrue(ShapeRecognition.hasDrawnWater(new ArrayList<>(Arrays.asList(WATER_SPELL_ARRAY_PATH))));
        assertFalse(ShapeRecognition.hasDrawnWater(new ArrayList<>(Arrays.asList(FIRE_SPELL_ARRAY_PATH))));
        assertFalse(ShapeRecognition.hasDrawnWater(new ArrayList<>(Arrays.asList(GROUND_SPELL_ARRAY_PATH))));

        System.out.println(strPrintFinish + "test_draw_water");
    }

    @Test
    public void test_draw_fire(){
        System.out.println(strPrintStart + "test_draw_fire");

        assertTrue(ShapeRecognition.hasDrawnFire(new ArrayList<>(Arrays.asList(FIRE_SPELL_ARRAY_PATH))));
        assertFalse(ShapeRecognition.hasDrawnFire(new ArrayList<>(Arrays.asList(WATER_SPELL_ARRAY_PATH))));
        assertFalse(ShapeRecognition.hasDrawnFire(new ArrayList<>(Arrays.asList(GROUND_SPELL_ARRAY_PATH))));

        System.out.println(strPrintFinish + "test_draw_fire");
    }

    @Test
    public void test_draw_ground(){
        System.out.println(strPrintStart + "test_draw_ground");

        assertTrue(ShapeRecognition.hasDrawnGround(new ArrayList<>(Arrays.asList(GROUND_SPELL_ARRAY_PATH))));
        assertFalse(ShapeRecognition.hasDrawnGround(new ArrayList<>(Arrays.asList(FIRE_SPELL_ARRAY_PATH))));
        assertFalse(ShapeRecognition.hasDrawnGround(new ArrayList<>(Arrays.asList(WATER_SPELL_ARRAY_PATH))));

        System.out.println(strPrintFinish + "test_draw_ground");
    }
}
