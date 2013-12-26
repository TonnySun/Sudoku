package com.example.sd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyView extends ViewGroup
{
	
	private final int COLUM = 10;

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
		getChildAt(0).layout(0, 0, getWidth(), (int)(getHeight() * 9 / COLUM));
		
		getChildAt(1).layout(0, (int)(getHeight() * 9 / COLUM), getWidth(), getHeight());
		
	}


}
