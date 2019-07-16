package com.spellrush.business.spellRecognition;

import android.graphics.PointF;

import java.util.ArrayList;

public final class WaterRecognition {
    private final static int MAX_NUM_OF_SEQ_ERRS = 5;

    /*
        Either a 'W' or a 'M'
    */
    public static boolean hasDrawnWith(ArrayList<PointF> points){
        boolean apexFlag1 = false;
        boolean antapexFlag = false;
        boolean apexFlag2 = false;
        boolean notContinuousFlag = false;

        int numOfSeqErrs = 0;
        PointF prevPoint = null;

        //Assume there's a path, therefore there are at least two points
        boolean headingRight = points.get(0).x < points.get(1).x;

        //Assume there's a path, therefore there are at least two points
        if(points.get(0).y > points.get(1).y){
            //Starting upwards
            for(PointF point : points){
                if(prevPoint != null) {
                    //For all points
                    if (headingRight != point.x > prevPoint.x) {
                        numOfSeqErrs++;

                        if(numOfSeqErrs >= MAX_NUM_OF_SEQ_ERRS){
                            notContinuousFlag = true;
                        }
                    }
                    else{
                        numOfSeqErrs = 0;
                    }

                    if (!apexFlag2) {
                        if (antapexFlag) {
                            if (point.y > prevPoint.y) {
                                apexFlag2 = true;
                            }
                        } else if (apexFlag1) {
                            if (point.y < prevPoint.y) {
                                antapexFlag = true;
                            }
                        } else {
                            if (point.y > prevPoint.y) {
                                apexFlag1 = true;
                            }
                        }
                    }
                }

                prevPoint = point;
            }
        }
        else{
            //Starting downwards
            for(PointF point : points){
                if(prevPoint != null) {
                    //For all points
                    if (headingRight != point.x > prevPoint.x) {
                        numOfSeqErrs++;

                        if(numOfSeqErrs >= MAX_NUM_OF_SEQ_ERRS){
                            notContinuousFlag = true;
                        }
                    }
                    else{
                        numOfSeqErrs = 0;
                    }

                    if (!apexFlag2) {
                        if (antapexFlag) {
                            if (point.y < prevPoint.y) {
                                apexFlag2 = true;
                            }
                        } else if (apexFlag1) {
                            if (point.y > prevPoint.y) {
                                antapexFlag = true;
                            }
                        } else {
                            if (point.y < prevPoint.y) {
                                apexFlag1 = true;
                            }
                        }
                    }
                }

                prevPoint = point;
            }
        }

        return apexFlag1 && antapexFlag && apexFlag2 && !notContinuousFlag;
    }
}
