package com.mgtv.vlc.player;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.VideoView;

import momo.cn.edu.fjnu.androidutils.utils.DeviceInfoUtils;

@SuppressLint("NewApi")
public class MyVideoView extends VideoView {
    private static final String TAG = "MyVideoView";
    private Context mContext;
    private int mScreenWidth;
    private int mScreenHeight;
    private int mVideoWidth;
    private int mVideoHeight;

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr,
                       int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        init();
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public MyVideoView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {

        mScreenWidth = DeviceInfoUtils.getScreenWidth(mContext);
        mScreenHeight = DeviceInfoUtils.getScreenHeight(mContext);
        int navigationBarHeight = 0;
        int resourceId = mContext.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = mContext.getResources().getDimensionPixelSize(resourceId);
            Log.i(TAG, "init->navigationBarHeight:" + navigationBarHeight);
        }
        mScreenHeight += navigationBarHeight;
        Log.i(TAG, "init->mScreenWidth:" + mScreenWidth);
        Log.i(TAG, "init->mScreenHeight:" + mScreenHeight);
        mVideoWidth = mScreenWidth;
        mVideoHeight = mScreenHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mScreenWidth, mScreenHeight);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

}
