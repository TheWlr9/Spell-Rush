package com.spellrush.presentation.UI;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.graphics.PointF;

import com.spellrush.objects.GameObject;

import java.util.ArrayList;

public class FingerPathLayer extends GameObject {
    private static final int FINGER_PATH_DEPTH = -50;
    private static final int MAX_NUM_OF_POINTS = 1000;
    private ArrayList<PointF> path, lastPath;
    private Paint paint;
    private PointF point;

    public FingerPathLayer(){
        super(FINGER_PATH_DEPTH);

        path = new ArrayList<>();
        lastPath = new ArrayList<>();

        setupPaint();
    }

    public FingerPathLayer(boolean debug){
        super(FINGER_PATH_DEPTH);

        path = new ArrayList<>();
        lastPath = new ArrayList<>();

        paint = new Paint();
    }

    private void setupPaint(){
        paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
    }

    @Override
    public void update() {
        //DUMMY
    }

    @Override
    public void draw(Canvas canvas) {
        if(canvas != null && path != null) {
            for (int i = 1; i < path.size(); i++) {
                canvas.drawLine(path.get(i - 1).x, path.get(i - 1).y, path.get(i).x, path.get(i).y, paint);
            }
        }
    }

    private void touchStart(){
        point = new PointF(); //Clear the point
    }

    private void touchMove(float x, float y){
        if(path.size() < MAX_NUM_OF_POINTS) {
            point.x = x;
            point.y = y;

            //Deep copy
            PointF temp = new PointF(point.x, point.y);
            path.add(temp);
        }
    }

    private void touchEnd(){
        lastPath.clear();
        
        //Deep copy
        for (int i = 0; i < path.size(); i++){
            PointF temp = new PointF(path.get(i).x, path.get(i).y);
            lastPath.add(temp);
        }

        path.clear();
    }

    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                touchStart();
                break;
            case MotionEvent.ACTION_MOVE :
                touchMove(x, y);
                break;
            case MotionEvent.ACTION_UP :
                touchEnd();
                break;
        }

        return true;
    }

    /**
     * 
     * @return The latest drawn path after the user raises their finger from the screen.
     */
    public ArrayList<PointF> getPath(){
        return lastPath;
    }

    public void resetPath(){
        path.clear();
        lastPath.clear();
    }
}
