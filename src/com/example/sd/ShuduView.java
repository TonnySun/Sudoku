package com.example.sd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ShuduView extends View
{
	private int width;
	
	private int height;
	
	private int x;
	
	private int y;
	
	private Game game;
	
	private int selectX;
	
	private int selectY;
	
	private int level = 1;

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
		game.setLevel(level);
		game.processSudokuNumber();
		updateSudokuView();
	}
	
	public void updateSudokuView()
	{
		invalidate();
	}

	public ShuduView(Context context)
	{
		
		super(context);

		game = new Game(level);
	}
	
	public void setBounds (int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		width = (int)(w / 9f);
		height = (int)(h /9f);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		Paint backgroundPaint = new Paint();
		backgroundPaint.setColor(getResources().getColor(R.color.shudu_background));
		canvas.drawRect(x, y, getWidth(), getHeight(), backgroundPaint);
		
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
		
		//调出一个对话框，显示1-9，并且将不可用的数字灰显
//		KeyDialog keyDialog = new KeyDialog(getContext(), used, this);
//		keyDialog.show();
		
		//尝试使用输入法
//        setFocusable(true);
//		InputMethodManager inputMethodManager = (InputMethodManager)(getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
//		inputMethodManager.showSoftInput(this, 0);
//		boolean flag = inputMethodManager.isActive();
		
		
		InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE); 
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);  
        
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
