package com.example.sd;

import java.util.Random;

public class Game
{
//	private final String str = "360000000" + "004230800" + "000004200"
//			+ "070460003" + "820000014" + "500013020" + "001900000"
//			+ "007048300" + "000000045";

	private int[][] shuduNumber = new int[9][9];

	private int[][] revertNumber = new int[9][9];

	private ShuDu shuduGenerate;

	private int level;

	public void setLevel(int level)
	{
		this.level = level;
	}

	private int[] revertNum = { 4, 6, 7 };

	public Game(int level)
	{
		this.level = level;
		processSudokuNumber();
	}
	
	public void processSudokuNumber ()
	{
//		if (shuduGenerate == null)
		{
			shuduGenerate = new ShuDu();
		}
		revertNumber = shuduGenerate.getAllNumber();
		shuduNumber = revertNumber.clone();
		processShowNumber();
	}
	
	private void processShowNumber()
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < revertNum[level - 1]; j++)
			{
				int randomInt= (int)(Math.random() * 10);
				if (randomInt > 8)
				{
					randomInt = 8;
				}
				if (shuduNumber[randomInt][i] != 0)
				{
					shuduNumber[randomInt][i] = 0;
				}
				else
				{
					randomInt= (int)(Math.random() * 10);
					if (randomInt > 8)
					{
						randomInt = 8;
					}
					shuduNumber[randomInt][i] = 0;
				}
			}
		}
	}

	private int getTile(int x, int y)
	{
		return shuduNumber[x][y];
	}

	public void setTile(int x, int y, int num)
	{
		shuduNumber[x][y] = num;
	}

	public String getTileString(int x, int y)
	{
		int v = getTile(x, y);
		if (v == 0)
		{
			return "";
		}
		else
		{
			return String.valueOf(v);
		}
	}

	protected int[] fromPuzzleString(String str)
	{
		int[] sd = new int[str.length()];

		for (int i = 0; i < sd.length; i++)
		{
			sd[i] = str.charAt(i) - '0';
		}

		return sd;
	}

	public int[] getSingleUsed(int x, int y)
	{
		int[] singleUsed = new int[9];

		// 统计行方向不可用的数字
		for (int i = 0; i < 9; i++)
		{
			int temp = getTile(i, x);
			if (temp != 0)
			{
				singleUsed[temp - 1] = temp;
			}
		}

		// 统计列方向已有的数字
		for (int i = 0; i < 9; i++)
		{
			int temp = getTile(y, i);
			if (temp != 0)
			{
				singleUsed[temp - 1] = temp;
			}
		}

		int startX = (x / 3) * 3;
		int startY = (y / 3) * 3;
		for (int i = startX; i < startX + 3; i++)
		{
			for (int j = startY; j < startY + 3; j++)
			{
				int temp = getTile(j, i);
				if (temp != 0)
				{
					singleUsed[temp - 1] = temp;
				}
			}
		}

		return singleUsed;
	}
}
