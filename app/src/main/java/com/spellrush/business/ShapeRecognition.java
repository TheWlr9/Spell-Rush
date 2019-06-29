package com.spellrush.business;

import android.graphics.PointF;

import com.spellrush.objects.attacks.AttackFactory;
import com.spellrush.presentation.UI.FingerPathLayer;

import java.util.ArrayList;

public final class ShapeRecognition {

    private final static int MAX_NUM_OF_SEQ_ERRS = 5;

    private ArrayList<PointF> points, lastPoints;
    private FingerPathLayer lFPL;

    public ShapeRecognition(FingerPathLayer FPRef){
        lFPL = FPRef;
        points = lFPL.getPath();
    }

    /*
        Draw a heart (supposed to be like a pointy heart with a smooth bottom [like a fire icon!])
     */
    public static boolean hasDrawnFire(ArrayList<PointF> points){
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

    /*
        Either a 'W' or a 'M'
     */
    public static boolean hasDrawnWater(ArrayList<PointF> points){
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

    /*
        Draw a single hump
     */
    public static boolean hasDrawnGround(ArrayList<PointF> points){
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

    public void hasValidDrawnEvent(){
        if(lastPoints == null || !lastPoints.equals(points)){
            lastPoints = getDeepCopy(points);

            if(points.size() >= 2) {
                if (hasDrawnFire(points)) {
                    //SEND EVENT
                    System.out.println("Fire event!");
                    AttackFactory.createFireAttack(true, 0);
                } else if (hasDrawnWater(points)) {
                    //SEND EVENT
                    System.out.println("Water event!");
                    AttackFactory.createWaterAttack(true, 0);
                } else if (hasDrawnGround(points)) {
                    //SEND EVENT
                    System.out.println("Ground event!");
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
