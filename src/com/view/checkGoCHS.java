package com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import com.check.checkGoActivity;

public class checkGoCHS extends HorizontalScrollView {
	
	checkGoActivity activity;
	
	public checkGoCHS(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		activity = (checkGoActivity) context;
	}

	
	public checkGoCHS(Context context, AttributeSet attrs) {
		super(context, attrs);
		activity = (checkGoActivity) context;
	}

	public checkGoCHS(Context context) {
		super(context);
		activity = (checkGoActivity) context;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		//���д�����ֵ
		activity.mTouchView = this;
		return super.onTouchEvent(ev);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		//����ǰ��CHSCrollView������ʱ����������
		if(activity.mTouchView == this) {
			activity.onScrollChanged(l, t, oldl, oldt);
		}else{
			super.onScrollChanged(l, t, oldl, oldt);
		}
	}
}