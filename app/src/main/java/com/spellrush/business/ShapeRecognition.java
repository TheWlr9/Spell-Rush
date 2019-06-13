package com.spellrush.business;

import android.graphics.PointF;

import com.spellrush.presentation.UI.FingerPathLayer;

import java.util.ArrayList;

public final class ShapeRecognition {
    private ArrayList<PointF> points, lastPoints;
    private FingerPathLayer lFPL;

    public ShapeRecognition(FingerPathLayer FPRef){
        lFPL = FPRef;
        points = lFPL.getPath();
    }

    private boolean hasDrawnFire(){
        //points != null
        float maxX = -1;
        boolean wrapping = false;
        PointF lastPoint = null;

        boolean result = true;


        for(PointF point : points){
            if(wrapping){
                if(lastPoint != null && point.x > lastPoint.x){
                    //Supposed to be heading leftwards
                    result = false;
                }
            }
            else if(point.x >= maxX) {
                //Heading rightwards
                maxX = point.x;
            }
            else {
                //One state for beginning to head leftwards
                wrapping = true;
            }

            lastPoint = new PointF(point.x, point.y); //Deep copy
        }

        return result && wrapping; //Ensure that the motion wraps and is valid
    }

    private void hasFireDrawnEvent(){
        if(lastPoints == null || !lastPoints.equals(points)){
            lastPoints = getDeepCopy(points);

            if(hasDrawnFire()) {
                //SEND EVENT
                System.out.println("Fire event!");
            }
        }
    }

    public void hasValidDrawnEvent(){
        hasFireDrawnEvent();
    }

    private ArrayList getDeepCopy(ArrayList list){
        ArrayList newList = new ArrayList();

        newList.addAll(list);

        return newList;
    }
}
