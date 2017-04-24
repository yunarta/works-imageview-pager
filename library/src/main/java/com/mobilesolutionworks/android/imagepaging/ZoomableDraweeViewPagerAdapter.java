package com.mobilesolutionworks.android.imagepaging;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import jp.co.vcube.android.gate.ux.fresco.plugin.zoomable.DefaultZoomableController;
import jp.co.vcube.android.gate.ux.fresco.plugin.zoomable.ZoomableController;

public abstract class ZoomableDraweeViewPagerAdapter extends PagerAdapter {

    SparseArray<ZoomableDraweeViewForPager> mImageViewPagerMap;

    public ZoomableDraweeViewPagerAdapter() {
        mImageViewPagerMap = new SparseArray<>();
    }

    protected abstract void setupImageView(ImageView imageView, int position);

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ZoomableDraweeViewForPager imageView = createViewPager(container);
        ZoomableController c = imageView.getZoomableController();
        if (c instanceof DefaultZoomableController) {
            DefaultZoomableController controller = (DefaultZoomableController) c;
            controller.setMaxScaleFactor(4.0f);
        }

        mImageViewPagerMap.put(position, imageView);

        ImageViewPager pager = (ImageViewPager) container;

        ViewGroup.LayoutParams params = pager.generateDefaultLayoutParams();
        params.width = container.getMeasuredWidth();
        params.height = container.getMeasuredHeight();

        container.addView(imageView, params);
        setupImageView(imageView, position);
        return imageView;
    }

    @NonNull
    protected abstract ZoomableDraweeViewForPager createViewPager(ViewGroup container);

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
        mImageViewPagerMap.delete(position);
    }

    public ZoomableDraweeViewForPager getImage(int position) {
        return mImageViewPagerMap.get(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

        ZoomableDraweeViewForPager pager;

        pager = mImageViewPagerMap.get(position - 1);
        if (pager != null) {
            pager.resetZoom();
        }

        pager = mImageViewPagerMap.get(position + 1);
        if (pager != null) {
            pager.resetZoom();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object; // NOPMD
    }
}
