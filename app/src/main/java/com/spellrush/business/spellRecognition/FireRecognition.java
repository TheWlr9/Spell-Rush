package com.spellrush.business.spellRecognition;

import android.graphics.PointF;

import com.spellrush.business.ShapeRecognition;

import java.util.ArrayList;

public final class FireRecognition {
    private final static int MAX_NUM_OF_SEQ_ERRS = 6;

    /*
        Draw a heart (supposed to be like a pointy heart with a smooth bottom [like a fire icon!])
    */
    public static boolean hasDrawnWith(ArrayList<PointF> points){
        boolean result = false;

        int numOfSeqErrs = 0;

        boolean headingUpwards = points.get(0).y > points.get(1).y;
        boolean headingRightwards = points.get(0).x < points.get(1).x;

        PointF lastPoint = null;

        if(headingUpwards && headingRightwards){
            //Starting at top left

            float startingY = points.get(0).y;

            boolean topApexFlag1 = false;
            boolean topAntapexFlag = false;
            boolean topApexFlag2 = false;
            boolean wrappingFlag = false;
            boolean botCleanupFlag = false;
            boolean topNotContinuousFlag = false;

            for(PointF point : points){
                if(lastPoint != null){
                    if (!topApexFlag2) { //Cell 0
                        if(point.x < lastPoint.x){
                            numOfSeqErrs++;

                            if(numOfSeqErrs >= MAX_NUM_OF_SEQ_ERRS){
                                topNotContinuousFlag = true;
                            }
                        }
                        else{
                            numOfSeqErrs = 0;
                        }

                        if (topAntapexFlag) {
                            if (point.y > lastPoint.y) {
                                topApexFlag2 = true; //Cell 0.75
                            }
                        } else if (topApexFlag1) {
                            if (point.y < lastPoint.y) {
                                topAntapexFlag = true; //Cell 0.5
                            }
                        } else {
                            if (point.y > lastPoint.y) {
                                topApexFlag1 = true; //Cell 0.25
                            }
                        }
                    } else { //Cell 1
                        if(wrappingFlag){
                            if(point.x < lastPoint.x && point.y < lastPoint.y){
                                botCleanupFlag = true; //Cell 1.6
                            }
                        } else {
                            if(point.y >= startingY && point.x < lastPoint.x){
                                wrappingFlag = true; //Cell 1.3
                            }
                        }
                    }
                }

                lastPoint = point;
            }

            result = botCleanupFlag && !topNotContinuousFlag;
        } else if(headingUpwards && !headingRightwards){
            //Starting top right

            float startingY = points.get(0).y;

            boolean topApexFlag1 = false;
            boolean topAntapexFlag = false;
            boolean topApexFlag2 = false;
            boolean wrappingFlag = false;
            boolean botCleanupFlag = false;
            boolean topNotContinuousFlag = false;

            for(PointF point : points){
                if(lastPoint != null){
                    if (!topApexFlag2) { //Cell 0
                        if(point.x > lastPoint.x){
                            numOfSeqErrs++;

                            if(numOfSeqErrs >= MAX_NUM_OF_SEQ_ERRS){
                                topNotContinuousFlag = true;
                            }
                        }
                        else{
                            numOfSeqErrs = 0;
                        }

                        if (topAntapexFlag) {
                            if (point.y > lastPoint.y) {
                                topApexFlag2 = true; //Cell 0.75
                            }
                        } else if (topApexFlag1) {
                            if (point.y < lastPoint.y) {
                                topAntapexFlag = true; //Cell 0.5
                            }
                        } else {
                            if (point.y > lastPoint.y) {
                                topApexFlag1 = true; //Cell 0.25
                            }
                        }
                    } else { //Cell 1
                        if(wrappingFlag){
                            if(point.x > lastPoint.x && point.y < lastPoint.y){
                                botCleanupFlag = true; //Cell 1.6
                            }
                        } else {
                            if(point.y >= startingY && point.x > lastPoint.x){
                                wrappingFlag = true; //Cell 1.3
                            }
                        }
                    }
                }

                lastPoint = point;
            }

            result = botCleanupFlag && !topNotContinuousFlag;
        } else if(!headingUpwards && headingRightwards){
            //Starting bottom left

            float startingY = points.get(0).y;

            boolean topApexFlag1 = false;
            boolean topAntapexFlag = false;
            boolean topApexFlag2 = false;
            boolean wrappingFlag = false;
            boolean botCleanupFlag = false;
            boolean topNotContinuousFlag = false;

            for(PointF point : points){
                if(lastPoint != null){
                    if(!wrappingFlag) { //Cell 0
                        if (botCleanupFlag) {
                            if (point.y <= startingY && point.x < lastPoint.x) {
                                wrappingFlag = true; //Cell 0.6
                            }
                        } else {
                            if (point.y < lastPoint.y) {
                                botCleanupFlag = true; //Cell 0.3
                            }
                        }
                    } else { //Cell 1
                        if(point.x > lastPoint.x){
                            numOfSeqErrs++;

                            if(numOfSeqErrs >= MAX_NUM_OF_SEQ_ERRS){
                                topNotContinuousFlag = true;
                            }
                        }
                        else{
                            numOfSeqErrs = 0;
                        }

                        if(topAntapexFlag){
                            if(point.y > lastPoint.y){
                                topApexFlag1 = true; //Cell 1.75
                            }
                        } else if(topApexFlag2){
                            if(point.y < lastPoint.y){
                                topAntapexFlag = true; //Cell 1.5
                            }
                        } else {
                            if(point.y > lastPoint.y){
                                topApexFlag2 = true; //Cell 1.25
                            }
                        }
                    }
                }

                lastPoint = point;
            }

            result = topApexFlag1 && !topNotContinuousFlag;
        }
        else{
            //Starting bottom right

            float startingY = points.get(0).y;

            boolean topApexFlag1 = false;
            boolean topAntapexFlag = false;
            boolean topApexFlag2 = false;
            boolean wrappingFlag = false;
            boolean botCleanupFlag = false;
            boolean topNotContinuousFlag = false;

            for(PointF point : points){
                if(lastPoint != null){
                    if(!wrappingFlag) { //Cell 0
                        if (botCleanupFlag) {
                            if (point.y <= startingY && point.x > lastPoint.x) {
                                wrappingFlag = true; //Cell 0.6
                            }
                        } else {
                            if (point.y < lastPoint.y) {
                                botCleanupFlag = true; //Cell 0.3
                            }
                        }
                    } else { //Cell 1
                        if(point.x < lastPoint.x){
                            numOfSeqErrs++;

                            if(numOfSeqErrs >= MAX_NUM_OF_SEQ_ERRS){
                                topNotContinuousFlag = true;
                            }
                        }
                        else{
                            numOfSeqErrs = 0;
                        }

                        if(topAntapexFlag){
                            if(point.y > lastPoint.y){
                                topApexFlag1 = true; //Cell 1.75
                            }
                        } else if(topApexFlag2){
                            if(point.y < lastPoint.y){
                                topAntapexFlag = true; //Cell 1.5
                            }
                        } else {
                            if(point.y > lastPoint.y){
                                topApexFlag2 = true; //Cell 1.25
                            }
                        }
                    }
                }

                lastPoint = point;
            }

            result = topApexFlag1 && !topNotContinuousFlag;
        }

        return result;
    }
}
