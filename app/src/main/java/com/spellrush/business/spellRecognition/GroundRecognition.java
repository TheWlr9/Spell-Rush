package com.spellrush.business.spellRecognition;

import android.graphics.PointF;

import java.util.ArrayList;

public final class GroundRecognition {
    private final static int MAX_NUM_OF_SEQ_ERRS = 8;

    /*
        Draw a single hump
    */
    public static boolean hasDrawnWith(ArrayList<PointF> points){
        //points != null
        float minY = 99999;
        boolean wrapping = false;
        boolean isStartValid = false;
        PointF lastPoint = null;
        boolean notContinuousFlag = false;

        int numOfSeqErrs = 0;

        //Assume there's a path, therefore there are at least two points
        boolean headingRight = points.get(0).x < points.get(1).x;

        boolean result = true;


        for(PointF point : points){
            //For all points
            if (lastPoint != null && headingRight != point.x > lastPoint.x) {
                numOfSeqErrs++;

                if(numOfSeqErrs >= MAX_NUM_OF_SEQ_ERRS){
                    notContinuousFlag = true;
                }
            }
            else{
                numOfSeqErrs = 0;
            }

            if(wrapping){
                if(lastPoint != null && point.y < lastPoint.y){
                    //Supposed to be heading downwards
                    result = false;
                }
            }
            else if(point.y <= minY) {
                //Heading upwards
                minY = point.y;
                isStartValid = true;
            }
            else {
                //One state for beginning to head downwards
                wrapping = true;
            }

            lastPoint =
                    point; //Deep copy
        }

        return result && wrapping && isStartValid && !notContinuousFlag; //Ensure that the motion wraps and is valid
    }
}
