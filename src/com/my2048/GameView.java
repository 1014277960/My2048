package com.my2048;

import java.util.Random;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class GameView extends View implements OnTouchListener, OnGestureListener {
	
	private GestureDetector mDetector;
	
	private Context context;

	private Block[][] map;
	
	private int width;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		mDetector = new GestureDetector(context, this);
		setLongClickable(true);
		setOnTouchListener(this);
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
	
	//��Ϸ��ʼ��
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
	
	//�����һ������������2��4
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

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float x,
			float y) {
		float X = e2.getX() - e1.getX();
		float Y = e2.getY() - e1.getY();
		if (X > 100) {
			Toast.makeText(context, "��", Toast.LENGTH_SHORT).show();
		} else if (X < -100) {
			Toast.makeText(context, "��", Toast.LENGTH_SHORT).show();
		} else if (Y > 100) {
			Toast.makeText(context, "��", Toast.LENGTH_SHORT).show();
		} else if (Y < -100) {
			Toast.makeText(context, "��", Toast.LENGTH_SHORT).show();
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float x,
			float y) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return mDetector.onTouchEvent(arg1);
	}

	
}
