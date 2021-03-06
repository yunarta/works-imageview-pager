package com.mobilesolutionworks.android.imagepaging;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public abstract class ImageViewTouchPagerAdapter extends PagerAdapter {

    Context mContext;

    SparseArray<ImageViewTouchForPager> mImageViewPagerMap;

    public ImageViewTouchPagerAdapter(Context context) {
        mContext = context;
        mImageViewPagerMap = new SparseArray<>();
    }

    protected abstract void setupImageView(ImageView imageView, int position);

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageViewTouchForPager imageView = new ImageViewTouchForPager(mContext);

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

    public ImageViewTouchForPager getImage(int position) {
        return mImageViewPagerMap.get(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

        ImageViewTouchForPager pager;

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
