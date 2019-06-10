package com.spellrush.buisness;

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

        boolean result = true;

        for(PointF point : points){
            if(wrapping){
                if(point.x > maxX){
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

    /*
    Point point = new Point(50, 50);

    final int CV_32F = 5;

    float[] yo = new float[20];

    ByteBuffer byteBuf = ByteBuffer.allocateDirect(yo.length * 4); //4 bytes per float

    private void Test(){
        byteBuf.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = byteBuf.asFloatBuffer();
        buffer.put(yo);
        buffer.position(0);
        Mat mat = new Mat(4, 4, CV_32F, byteBuf);
    }
    */
}
