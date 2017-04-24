package com.mobilesolutionworks.android.imagepaging;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;

import jp.co.vcube.android.gate.ux.fresco.plugin.zoomable.DefaultZoomableController;
import jp.co.vcube.android.gate.ux.fresco.plugin.zoomable.ZoomableDraweeView;

public class ZoomableDraweeViewForPager extends ZoomableDraweeView implements Scrollable {

    public ZoomableDraweeViewForPager(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public ZoomableDraweeViewForPager(Context context) {
        super(context, (AttributeSet) null);
    }

    public ZoomableDraweeViewForPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZoomableDraweeViewForPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//    public RectF getZoomedRect() {
//        RectF rect = new RectF();
//        getImageBounds(rect);
//
//        Matrix transform = getZoomableController().getTransform();
//        transform.mapRect(rect);
//
//        return rect;
//    }

    @Override
    public boolean isHittingEdge(int direction) {
        RectF rect = getZoomedRect();
        if (rect == null) {
            return true;
        }
        if (direction > 0) {
            return (int) rect.right <= getWidth();
        } else {
            return (int) rect.left >= 0;
        }
    }

    @Override
    public boolean isScrollable() {
        return true;
    }

    public void resetZoom() {
        DefaultZoomableController zoomableController = (DefaultZoomableController) getZoomableController();
        zoomableController.zoomToPoint(1, new PointF(), new PointF());
    }
}
