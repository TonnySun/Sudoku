package com.example.sd;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class KeyDialog extends Dialog
{
	private View[] button = new View[9];
	
	private int[] used;
	
	private ShuduView shuduView;

	public KeyDialog(Context context, int[] used, ShuduView shuduView)
	{
		super(context);
		setContentView(R.layout.keydialog);
		
		setTitle("选择可用数字");
		// TODO Auto-generated constructor stub
		
		this.used = used;
		this.shuduView = shuduView;
		
		getViewButton();
		
		setListener();
	}

	
	private void getViewButton()
	{
		
		for (int i = 0; i < 9; i++)
		{
			button[i] = (Button)findViewById(R.id.key1 + i);
			final int num = i + 1;
			if (used[i] != 0)
			{
				button[i].setEnabled(false);
			}
		}
		
	}
	
	private void setResult (int num)
	{
		shuduView.setNum(num);
		dismiss();
	}
	
	private void setListener ()
	{
		for (int i = 0; i < 9; i++)
		{
			final int num = i + 1;
			button[i].setOnClickListener(new View.OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stu
					setResult(num);
				}
			});
		}
	}
}
