package com.spellrush.business;

import android.graphics.PointF;

public class ShapeRecognitionTests {
    public static class StubPointF extends PointF {
        StubPointF(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public final static PointF[] FIRE_SPELL_ARRAY_PATH = {new StubPointF(263, 1082),
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

    public final static PointF[] GROUND_SPELL_ARRAY_PATH = {new StubPointF(273, 1314),
            new StubPointF(283, 1289), new StubPointF(313, 1244), new StubPointF(335, 1212),
            new StubPointF(380, 1170), new StubPointF(418, 1143), new StubPointF(468, 1115),
            new StubPointF(489, 1105), new StubPointF(547, 1086), new StubPointF(603, 1076),
            new StubPointF(653, 1078), new StubPointF(685, 1086), new StubPointF(730, 1101),
            new StubPointF(762, 1122), new StubPointF(779, 1135), new StubPointF(806, 1166),
            new StubPointF(837, 1210), new StubPointF(856, 1241), new StubPointF(880, 1281),
            new StubPointF(890, 1296)};

    public final static PointF[] WATER_SPELL_ARRAY_PATH = {new StubPointF(189, 1050),
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

   public final static PointF[] FIRE_TOPRIGHT_PATH = {
           new StubPointF(934, 892), new StubPointF(934, 869), new StubPointF(931, 843),
           new StubPointF(925, 814), new StubPointF(912, 787), new StubPointF(900, 764),
           new StubPointF(890, 742), new StubPointF(881, 726), new StubPointF(875, 718),
           new StubPointF(871, 720), new StubPointF(866, 720), new StubPointF(859, 728),
           new StubPointF(848, 742), new StubPointF(834, 760), new StubPointF(818, 779),
           new StubPointF(804, 800), new StubPointF(790, 817), new StubPointF(781, 832),
           new StubPointF(771, 839), new StubPointF(764, 845), new StubPointF(756, 848),
           new StubPointF(753, 851), new StubPointF(752, 851), new StubPointF(748, 851),
           new StubPointF(741, 848), new StubPointF(729, 836), new StubPointF(711, 813),
           new StubPointF(690, 785), new StubPointF(668, 757), new StubPointF(649, 732),
           new StubPointF(634, 710), new StubPointF(624, 698), new StubPointF(613, 691),
           new StubPointF(607, 688), new StubPointF(604, 688), new StubPointF(602, 688),
           new StubPointF(599, 690), new StubPointF(590, 698), new StubPointF(579, 718),
           new StubPointF(568, 739), new StubPointF(558, 765), new StubPointF(546, 793),
           new StubPointF(537, 814), new StubPointF(533, 830), new StubPointF(529, 835),
           new StubPointF(524, 826), new StubPointF(509, 804), new StubPointF(487, 779),
           new StubPointF(459, 748), new StubPointF(430, 725), new StubPointF(412, 709),
           new StubPointF(399, 701), new StubPointF(391, 698), new StubPointF(387, 695),
           new StubPointF(380, 695), new StubPointF(374, 695), new StubPointF(366, 695),
           new StubPointF(355, 695), new StubPointF(342, 698), new StubPointF(324, 707),
           new StubPointF(305, 723), new StubPointF(284, 742), new StubPointF(268, 757),
           new StubPointF(255, 782), new StubPointF(243, 811), new StubPointF(233, 842),
           new StubPointF(224, 876), new StubPointF(221, 905), new StubPointF(221, 936),
           new StubPointF(221, 966), new StubPointF(227, 991), new StubPointF(236, 1012),
           new StubPointF(249, 1033), new StubPointF(268, 1055), new StubPointF(288, 1073),
           new StubPointF(305, 1086), new StubPointF(327, 1102), new StubPointF(349, 1118),
           new StubPointF(373, 1133), new StubPointF(393, 1146), new StubPointF(415, 1159),
           new StubPointF(436, 1170), new StubPointF(457, 1183), new StubPointF(480, 1190),
           new StubPointF(504, 1196), new StubPointF(527, 1203), new StubPointF(549, 1203),
           new StubPointF(570, 1203), new StubPointF(590, 1203), new StubPointF(612, 1199),
           new StubPointF(637, 1190), new StubPointF(671, 1177), new StubPointF(706, 1159),
           new StubPointF(737, 1140), new StubPointF(764, 1122), new StubPointF(790, 1108),
           new StubPointF(815, 1095), new StubPointF(837, 1083), new StubPointF(856, 1077),
           new StubPointF(869, 1068), new StubPointF(878, 1055), new StubPointF(889, 1043),
           new StubPointF(898, 1027), new StubPointF(906, 1010), new StubPointF(913, 995),
           new StubPointF(922, 977), new StubPointF(928, 959), new StubPointF(934, 942),
           new StubPointF(940, 925), new StubPointF(947, 908), new StubPointF(953, 891),
           new StubPointF(956, 877), new StubPointF(959, 868), new StubPointF(959, 865),
           new StubPointF(959, 863), new StubPointF(956, 864) };

    public final static PointF[] FIRE_BOTRIGHT_PATH = {
            new StubPointF(716, 1140), new StubPointF(715, 1143), new StubPointF(715, 1146), new StubPointF(711, 1146),
            new StubPointF(708, 1149), new StubPointF(706, 1151), new StubPointF(703, 1152), new StubPointF(700, 1152),
            new StubPointF(694, 1155), new StubPointF(692, 1155), new StubPointF(690, 1155), new StubPointF(684, 1155),
            new StubPointF(676, 1155), new StubPointF(669, 1155), new StubPointF(663, 1155), new StubPointF(659, 1155),
            new StubPointF(649, 1155), new StubPointF(639, 1152), new StubPointF(633, 1149), new StubPointF(629, 1149),
            new StubPointF(626, 1146), new StubPointF(624, 1143), new StubPointF(621, 1140), new StubPointF(618, 1134),
            new StubPointF(618, 1126), new StubPointF(615, 1121), new StubPointF(615, 1114), new StubPointF(613, 1106),
            new StubPointF(612, 1099), new StubPointF(612, 1095), new StubPointF(612, 1092), new StubPointF(612, 1089),
            new StubPointF(612, 1085), new StubPointF(617, 1086), new StubPointF(621, 1086), new StubPointF(626, 1086),
            new StubPointF(631, 1090), new StubPointF(635, 1090), new StubPointF(636, 1093), new StubPointF(639, 1093),
            new StubPointF(640, 1090), new StubPointF(647, 1086), new StubPointF(649, 1080), new StubPointF(653, 1076),
            new StubPointF(658, 1077), new StubPointF(660, 1077), new StubPointF(663, 1077), new StubPointF(667, 1077),
            new StubPointF(670, 1077), new StubPointF(671, 1080), new StubPointF(676, 1080), new StubPointF(680, 1083),
            new StubPointF(678, 1088), new StubPointF(681, 1093), new StubPointF(681, 1096), new StubPointF(686, 1096),
            new StubPointF(690, 1096), new StubPointF(693, 1093), new StubPointF(702, 1090), new StubPointF(709, 1084),
            new StubPointF(717, 1083), new StubPointF(719, 1083), new StubPointF(725, 1088), new StubPointF(728, 1096),
            new StubPointF(734, 1105), new StubPointF(737, 1115), new StubPointF(740, 1120), new StubPointF(740, 1124),
            new StubPointF(740, 1125), new StubPointF(740, 1128), new StubPointF(740, 1130), new StubPointF(737, 1130)};

    public final static PointF[] WATER_DOWNWARDS_PATH = {
        new StubPointF(343, 1022), new StubPointF(352, 1044), new StubPointF(358, 1065), new StubPointF(368, 1090),
            new StubPointF(380, 1118), new StubPointF(396, 1146), new StubPointF(406, 1171), new StubPointF(415, 1184),
            new StubPointF(421, 1187), new StubPointF(428, 1191), new StubPointF(439, 1193), new StubPointF(450, 1196),
            new StubPointF(464, 1196), new StubPointF(475, 1196), new StubPointF(486, 1196), new StubPointF(498, 1191),
            new StubPointF(513, 1181), new StubPointF(537, 1162), new StubPointF(566, 1139), new StubPointF(593, 1118),
            new StubPointF(615, 1108), new StubPointF(630, 1105), new StubPointF(636, 1105), new StubPointF(643, 1110),
            new StubPointF(659, 1130), new StubPointF(678, 1159), new StubPointF(700, 1184), new StubPointF(723, 1203),
            new StubPointF(740, 1212), new StubPointF(754, 1215), new StubPointF(768, 1215), new StubPointF(787, 1203),
            new StubPointF(815, 1177), new StubPointF(843, 1149), new StubPointF(875, 1118), new StubPointF(903, 1086),
            new StubPointF(929, 1061), new StubPointF(944, 1047), new StubPointF(947, 1043) };
}
