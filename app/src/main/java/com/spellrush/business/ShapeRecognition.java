package com.spellrush.business;

import android.graphics.PointF;

import com.spellrush.business.spellRecognition.FireRecognition;
import com.spellrush.business.spellRecognition.GroundRecognition;
import com.spellrush.business.spellRecognition.WaterRecognition;
import com.spellrush.objects.attacks.AttackFactory;
import com.spellrush.presentation.UI.FingerPathLayer;

import java.util.ArrayList;

public class ShapeRecognition {
    private ArrayList<PointF> points, lastPoints;
    private FingerPathLayer lFPL;

    public ShapeRecognition(FingerPathLayer FPRef){
        lFPL = FPRef;
        points = lFPL.getPath();
    }

    public void hasValidDrawnEvent(){
        if(lastPoints == null || !lastPoints.equals(points)){
            lastPoints = getDeepCopy(points);

            if(points.size() >= 2) {
                if (FireRecognition.hasDrawnWith(points)) {
                    //SEND EVENT
                    //System.out.println("Fire event!");
                    AttackFactory.createFireAttack(true, 0);
                } else if (WaterRecognition.hasDrawnWith(points)) {
                    //SEND EVENT
                    //System.out.println("Water event!");
                    AttackFactory.createWaterAttack(true, 0);
                } else if (GroundRecognition.hasDrawnWith(points)) {
                    //SEND EVENT
                    //System.out.println("Ground event!");
                    AttackFactory.createGroundAttack(true, 0);
                }
            }
        }
    }

    private ArrayList getDeepCopy(ArrayList list){
        ArrayList newList = new ArrayList();

        newList.addAll(list);

        return newList;
    }
}
