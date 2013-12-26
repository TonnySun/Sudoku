package com.example.sd;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		
//		LinearLayout view = new LinearLayout(this);
//		setContentView(view);
//		
//		view.setOrientation(LinearLayout.VERTICAL);
//		
//		
		MyView view = new MyView(this, null);
		
		int width = getWindowManager().getDefaultDisplay().getWidth();
		int height = getWindowManager().getDefaultDisplay().getHeight();
		
//		view.setLayoutParams(new LinearLayout.LayoutParams(width, height));
		
		ShuduView shuduView = new ShuduView(this);
		MenuView menuView = new MenuView(this);
		
		
		view.addView(shuduView);
		view.addView(menuView);

		setContentView(view);
//		setContentView(new ShuduView(this));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
