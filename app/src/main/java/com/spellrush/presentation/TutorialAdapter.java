package com.spellrush.presentation;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.spellrush.R;

public class TutorialAdapter extends PagerAdapter {

    private Context myContext;
    private int[] tutImageIDs = new int[]{ R.drawable.firetut, R.drawable.watertut, R.drawable.groundtut};

    public TutorialAdapter(Context context) {
        myContext=context;
    }

    @Override
    public int getCount() {
        return tutImageIDs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iview = new ImageView(myContext);
        iview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iview.setImageResource(tutImageIDs[position]);
        container.addView(iview,0);
        return iview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);    }


}
