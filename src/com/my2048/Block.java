package com.my2048;

import android.R.integer;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;

public class Block {
	
	private int x;
	private int y;
	private int value;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getNextX() {
		if (y == 3) {
			if (x == 3) {
				return 0;
			} else {
				return x + 1;
			}
		} else {
			return x;
		}
	}
	
	public int getNextY() {
		if (y == 3) {
			return 0;
		} else {
			return y + 1;
		}
	}
	
	public void draw(Canvas canvas, int width) {
		if (value == 0) {
			return;
		}
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);
		paint.setTextSize(width / 4);
		paint.setTextAlign(Paint.Align.CENTER);
		FontMetrics fm = paint.getFontMetrics();
		canvas.drawText(value + "", width / 2 + y * width, 
				width / 2 + x * width - (fm.descent + fm.ascent) / 2, paint);
	}
	
}
