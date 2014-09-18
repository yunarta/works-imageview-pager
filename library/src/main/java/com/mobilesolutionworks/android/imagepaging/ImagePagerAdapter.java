package com.mobilesolutionworks.android.imagepaging;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by yunarta on 18/9/14.
 */
public abstract class ImagePagerAdapter extends PagerAdapter {

    Context mContext;

    SparseArray<ImageViewTouchForPager> mImageViewPagerMap;

    public ImagePagerAdapter(Context context) {
        mContext = context;
        mImageViewPagerMap = new SparseArray<ImageViewTouchForPager>();
    }

    protected abstract void setupImageView(ImageView imageView, int position);

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageViewTouchForPager imageView = new ImageViewTouchForPager(mContext);

        mImageViewPagerMap.put(position, imageView);

        container.addView(imageView);
        setupImageView(imageView, position);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
        mImageViewPagerMap.delete(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public boolean allowPageScroll(int position) {
//        ImageViewTouchForPager imageView = mImageViewPagerMap.get(position);
//        return imageView.isHittingEdge(-dx) && imageView.isScrollable();
        return false;
    }
}
