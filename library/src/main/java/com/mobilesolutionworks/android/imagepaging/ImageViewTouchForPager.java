package com.mobilesolutionworks.android.imagepaging;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by yunarta on 18/9/14.
 */
public class ImageViewTouchForPager extends ImageViewTouch implements Scrollable {

    public ImageViewTouchForPager(Context context) {
        super(context, null);
    }

    public ImageViewTouchForPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewTouchForPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isHittingEdge(int direction) {
        RectF rect = getZoomedRect();
        if (rect == null) return true;
        if (direction > 0) {
            return (int) rect.right <= getWidth();
        } else {
            return (int) rect.left >= 0;
        }
    }

    @Override
    public boolean isScrollable() {
//        boolean b = !mScaleDetector.isInProgress();
//        Log.d("[pager]", "isScrollable = " + b);

        return true;
    }
}
