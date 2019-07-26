package com.spellrush.presentation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.spellrush.R;
import com.spellrush.business.GameView;
import com.spellrush.objects.GameObject;

// This exists because the GameView can't be transparent on the GameActivity
public class BackgroundImage extends GameObject {
    public static final int BG_LAYER = 999999;
    private Bitmap bg = null;

    public BackgroundImage(){
        super(BG_LAYER);
    }

    private void initSprite(){
        Resources r = GameView.getInstance().getContext().getResources();
        bg = BitmapFactory.decodeResource(r, R.drawable.spellrush_bg_hq);

        // Scale to screen efficiently
        int dW = Resources.getSystem().getDisplayMetrics().widthPixels;
        int dH = Resources.getSystem().getDisplayMetrics().heightPixels;
        int myW = bg.getWidth();
        int myH = bg.getHeight();
        float scaleWidth = ((float) dW) / myW;
        float scaleHeight = ((float) dH) / myH;
        // Create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // Resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // Recreate the new Bitmap
        bg = Bitmap.createBitmap(bg, 0, 0, myW, myH, matrix, false);
        bg.setDensity(Bitmap.DENSITY_NONE);
    }

    @Override
    public void update() {
        // op
    }

    @Override
    public void draw(Canvas canvas) {
        if(bg == null){initSprite();}
        canvas.drawBitmap(bg,0,0,null);
    }
}
