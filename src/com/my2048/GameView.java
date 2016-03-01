package com.my2048;

import java.util.Random;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

public class GameView extends View {
	
	private Context context;

	private Block[][] map;
	
	private int width;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		width = getWidth() / 4;
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);
		for(int i = 0; i != 5; i++) {
			canvas.drawLine(0, i * width, getWidth(), i * width, paint);
		}
		for(int i = 0; i != 5; i++) {
			canvas.drawLine(i * width, 0, i * width, getWidth(), paint);
		}
		for (int i = 0; i != 4; i++) {
			for (int j = 0; j != 4; j++) {
				map[i][j].draw(canvas, width);
			}
		}
	}
	
	//游戏初始化
	private void init() {
		map = new Block[4][4];
		for (int i = 0; i != 4; i++) {
			for (int j = 0; j != 4; j++) {
				map[i][j] = new Block(i, j);
				map[i][j].setValue(0);
			}
		}
		random();
		random();
	}
	
	//随机在一个方块内生成2或4
	private void random() {
		int value;
		int x, y;
		Random random = new Random();
		value = random.nextInt(2) + 1;
		value *= 2;
		x = random.nextInt(4);
		y = random.nextInt(4);
		while (map[x][y].getValue() != 0) {
			x = map[x][y].getNextX();
			y = map[x][y].getNextY();
		}
		map[x][y].setValue(value);
	}
	
}
