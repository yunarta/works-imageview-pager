package com.mobilesolutionworks.android.imagepaging;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;

/**
 * Created by yunarta on 18/9/14.
 */
public class ImageViewTouchForPager extends ImageViewTouch implements Scrollable {

    public ImageViewTouchForPager(Context context) {
        super(context);
    }

    public ImageViewTouchForPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewTouchForPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isHittingEdge() {
        RectF rect = getBitmapRect();
        return (int) rect.right <= getWidth() || (int) rect.left >= 0;
    }

    @Override
    public boolean isScrollable() {
        return !mScaleDetector.isInProgress();
    }
}
