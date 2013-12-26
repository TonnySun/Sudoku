package com.example.sd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyView extends ViewGroup
{

	public MyView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		// TODO Auto-generated method stub
		for (int i = 0; i < getChildCount(); i++)
		{
			View view = getChildAt(i);
			view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		}
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		// TODO Auto-generated method stub
		getChildAt(0).layout(0, 0, 720, 800);
		getChildAt(1).layout(0, 800, 720, 300);
	}


}
