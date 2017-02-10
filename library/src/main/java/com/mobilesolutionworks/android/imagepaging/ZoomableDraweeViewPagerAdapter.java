package com.mobilesolutionworks.android.imagepaging;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by yunarta on 18/9/14.
 */
public abstract class ZoomableDraweeViewPagerAdapter extends PagerAdapter {

    SparseArray<ZoomableDraweeViewForPager> mImageViewPagerMap;

    public ZoomableDraweeViewPagerAdapter() {
        mImageViewPagerMap = new SparseArray<ZoomableDraweeViewForPager>();
    }

    protected abstract void setupImageView(ImageView imageView, int position);

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ZoomableDraweeViewForPager imageView = new ZoomableDraweeViewForPager(container.getContext());

        mImageViewPagerMap.put(position, imageView);

        ImageViewPager pager = (ImageViewPager) container;

        ViewGroup.LayoutParams params = pager.generateDefaultLayoutParams();
        params.width = container.getMeasuredWidth();
        params.height = container.getMeasuredHeight();

        container.addView(imageView, params);
        setupImageView(imageView, position);
        return imageView;
    }

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
        return view == object;
    }

    public boolean allowPageScroll(int position) {
//        ZoomableDraweeViewForPager imageView = mImageViewPagerMap.get(position);
//        return imageView.isHittingEdge(-dx) && imageView.isScrollable();
        return false;
    }
}
