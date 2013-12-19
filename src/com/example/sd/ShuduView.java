package com.example.sd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.view.MotionEvent;
import android.view.View;

public class ShuduView extends View
{
	private float width;
	
	private float height;
	
	private Game game;
	
	private int selectX;
	
	private int selectY;

	public ShuduView(Context context)
	{
		
		super(context);

		game = new Game();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		width = w / 9f;
		height = h /9f;
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		Paint backgroundPaint = new Paint();
		backgroundPaint.setColor(getResources().getColor(R.color.shudu_background));
		canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);
		
		Paint hilitePaint = new Paint();
		hilitePaint.setColor(getResources().getColor(R.color.shudu_hilte));
		
		Paint lightPaint = new Paint();
		lightPaint.setColor(getResources().getColor(R.color.shudu_light));
		
		Paint darkPaint = new Paint();
		darkPaint.setColor(getResources().getColor(R.color.shudu_dark));
		
		for (int i = 0; i < 9; i++)
		{
			canvas.drawLine(0, i * height, getWidth(), i * height, lightPaint);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilitePaint);
			canvas.drawLine(i * width, 0, i * width, getHeight(), lightPaint);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilitePaint);
			
			if (i % 3 != 0)
			{
				continue;
			}
			else
			{
				canvas.drawLine(0, i * height, getWidth(), i * height, darkPaint);
				canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilitePaint);
				
				canvas.drawLine(i * width, 0, i * width, getHeight(), darkPaint);
				canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilitePaint);
				
			}
		}
		
		Paint numberPaint = new Paint();
		numberPaint.setColor(Color.BLACK);
		numberPaint.setStyle(Paint.Style.STROKE);
		numberPaint.setTextSize(height * 0.75f);
		numberPaint.setTextAlign(Paint.Align.CENTER);
		
		FontMetrics fm = numberPaint.getFontMetrics();
		float x = width / 2;
		float y = height / 2 - (fm.ascent + fm.descent) / 2;
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{

				canvas.drawText(game.getTileString(i, j), i * width + x, j * height + y, numberPaint);
			}
		}
		
		super.onDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() != MotionEvent.ACTION_DOWN)
		{
			return super.onTouchEvent(event);
		}
		
		float eventX = event.getX();
		float eventY = event.getY();
		
		selectY = (int)(eventX / width);
		selectX = (int)(eventY / height);
		
		int[] used = game.getSingleUsed(selectX, selectY);
		
//		StringBuffer sb = new StringBuffer();
//		int index = 0;
//		
//		for (int temp : used)
//		{
//			
//			if (temp == 0)
//			{
//				sb.append(index + 1);
//			}
//			System.out.println(temp);
//			index++;
//		}
		
		KeyDialog keyDialog = new KeyDialog(getContext(), used, this);
		keyDialog.show();
		
//		LayoutInflater inflater = LayoutInflater.from(this.getContext());
//		View layoutView = inflater.inflate(R.layout.dialog, null);
//		
//		TextView unUsed = (TextView)layoutView.findViewById(R.id.unusednum);
//		unUsed.setText(sb.toString());
//		
//		AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
//		builder.setView(layoutView);
//		AlertDialog dialog = builder.create();
//		dialog.show();
		
		return true;
	}
	
	public void setNum (int num)
	{
		game.setTile(selectY, selectX, num);
		invalidate();
	}

}
