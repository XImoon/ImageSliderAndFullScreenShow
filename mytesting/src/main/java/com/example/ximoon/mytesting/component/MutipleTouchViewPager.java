package com.example.ximoon.mytesting.component;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 多点触控的viewpager
 */
public class MutipleTouchViewPager extends ViewPager {

	public MutipleTouchViewPager(Context context) {
		super(context);
	}

	public MutipleTouchViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private boolean mIsDisallowIntercept = false;

	@Override
	public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
		// keep the info about if the innerViews do
		// requestDisallowInterceptTouchEvent
		mIsDisallowIntercept = disallowIntercept;
		super.requestDisallowInterceptTouchEvent(disallowIntercept);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// the incorrect array size will only happen in the multi-touch
		// scenario.
		if (ev.getPointerCount() > 1 && mIsDisallowIntercept) {
			requestDisallowInterceptTouchEvent(false);
			boolean handled = super.dispatchTouchEvent(ev);
			requestDisallowInterceptTouchEvent(true);
			return handled;
		} else {
			return super.dispatchTouchEvent(ev);
		}
	}
}
