package com.spellrush.presentation.Views.Layers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class FingerPathLayer extends GameViewLayer {
    private static Paint MY_PAINT = new Paint();

    private Path circlePath = new Path();


    public FingerPathLayer(Context context){
        super(context);

        MY_PAINT.setColor(Color.CYAN);
        MY_PAINT.setStrokeWidth(4f);

        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if(circlePath != null) {
            canvas.drawPath(circlePath, MY_PAINT);
        }
    }

    private void touchStart(float x, float y){
        circlePath = new Path();
    }

    private void touchMove(float x, float y){
        circlePath.addCircle(x, y, 30, Path.Direction.CW);
    }

    private void touchEnd(){
        circlePath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                touchEnd();
                invalidate();
                break;
        }

        return true;
    }
}
