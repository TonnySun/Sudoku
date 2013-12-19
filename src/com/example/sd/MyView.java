package com.example.sd;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.widget.SlidingPaneLayout.PanelSlideListener;
import android.view.View;

public class MyView extends View
{

	public MyView(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		Paint paint = new Paint();
		
		paint.setColor(Color.RED);
		
		canvas.drawLine(50, 50, 400, 400, paint);
		
		canvas.drawRect(100, 100, 300, 300, paint);
		
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(20);
		paint.setARGB(120, 100, 50, 200);
		
		canvas.drawCircle(250, 250, 66, paint);
		
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), 50, 50, paint);
	}
}
