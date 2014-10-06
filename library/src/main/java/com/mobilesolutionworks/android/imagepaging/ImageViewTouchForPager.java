package com.mobilesolutionworks.android.imagepaging;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by yunarta on 18/9/14.
 */
public class ImageViewTouchForPager extends TouchImageView implements Scrollable {

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

//        Log.d("[pager]", "isHittingEdge direction = " + direction);
        if (direction > 0) {
            boolean b = (int) rect.right <= getWidth();
//            Log.d("[pager]", "isHittingEdge right result = " + b);

            return b;
        } else {
            boolean b = (int) rect.left >= 0;
//            Log.d("[pager]", "isHittingEdge left result = " + b);

            return b;
        }
    }

    @Override
    public boolean isScrollable() {
//        boolean b = !mScaleDetector.isInProgress();
//        Log.d("[pager]", "isScrollable = " + b);

        return true;
    }
}
