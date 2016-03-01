package com.my2048;

import java.util.Random;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class GameView extends View implements OnTouchListener, OnGestureListener {
	
	private final int MOVE_LEFT = 0;
	private final int MOVE_RIGHT = 1;
	private final int MOVE_UP = 2;
	private final int MOVE_DOWN = 3;
	
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

	private void move(int direct) {
		if (direct == MOVE_LEFT) {
			boolean change = false;
			for (int i = 0; i != 4; i++) {
				int[] row = new int[4];
				int a = 0;
				if (map[i][0].getValue() != 0) {
					row[a] = map[i][0].getValue();
					if (a != 0) {
						change = true;
					}
					a++;
				}
				if (map[i][1].getValue() != 0) {
					row[a] = map[i][1].getValue();
					if (a != 1) {
						change = true;
					}
					a++;
				}
				if (map[i][2].getValue() != 0) {
					row[a] = map[i][2].getValue();
					if (a != 2) {
						change = true;
					}
					a++;
				}
				if (map[i][3].getValue() != 0) {
					row[a] = map[i][3].getValue();
					if (a != 3) {
						change = true;
					}
					a++;
				}
				for (int j = 0; j != 3; j++) {
					if ((row[j] == row[j + 1]) && (row[j] != 0)) {
						row[j] *= 2;
						j++;
						if (j == 1) {
							row[1] = row[2];
							row[2] = row[3];
							row[3] = 0;
						} else if (j == 2) {
							row[2] = row[3];
							row[3] = 0;
						} else if (j == 3) {
							row[3] = 0;
						}
						change = true;
						break;
					}
				}
				for (int k = 0; k != 4; k++) {
					map[i][k].setValue(row[k]);
				}
			}
			if (change) {
				random();
			}
			invalidate();
			return;
		}
		if (direct == MOVE_RIGHT) {
			boolean change = false;
			for (int i = 0; i != 4; i++) {
				int[] row = new int[4];
				int a = 0;
				if (map[i][3].getValue() != 0) {
					row[a] = map[i][3].getValue();
					if (a != 0) {
						change = true;
					}
					a++;
				}
				if (map[i][2].getValue() != 0) {
					row[a] = map[i][2].getValue();
					if (a != 1) {
						change = true;
					}
					a++;
				}
				if (map[i][1].getValue() != 0) {
					row[a] = map[i][1].getValue();
					if (a != 2) {
						change = true;
					}
					a++;
				}
				if (map[i][0].getValue() != 0) {
					row[a] = map[i][0].getValue();
					if (a != 3) {
						change = true;
					}
					a++;
				}
				for (int j = 0; j != 3; j++) {
					if ((row[j] == row[j + 1]) && (row[j] != 0)) {
						row[j] *= 2;
						j++;
						if (j == 1) {
							row[1] = row[2];
							row[2] = row[3];
							row[3] = 0;
						} else if (j == 2) {
							row[2] = row[3];
							row[3] = 0;
						} else if (j == 3) {
							row[3] = 0;
						}
						change = true;
						break;
					}
				}
				for (int k = 0; k != 4; k++) {
					map[i][k].setValue(row[3 - k]);
				}
			}
			if (change) {
				random();
			}
			invalidate();
			return;
		}
		if (direct == MOVE_UP) {
			boolean change = false;
			for (int i = 0; i != 4; i++) {
				int[] row = new int[4];
				int a = 0;
				if (map[0][i].getValue() != 0) {
					row[a] = map[0][i].getValue();
					if (a != 0) {
						change = true;
					}
					a++;
				}
				if (map[1][i].getValue() != 0) {
					row[a] = map[1][i].getValue();
					if (a != 1) {
						change = true;
					}
					a++;
				}
				if (map[2][i].getValue() != 0) {
					row[a] = map[2][i].getValue();
					if (a != 2) {
						change = true;
					}
					a++;
				}
				if (map[3][i].getValue() != 0) {
					row[a] = map[3][i].getValue();
					if (a != 3) {
						change = true;
					}
					a++;
				}
				for (int j = 0; j != 3; j++) {
					if ((row[j] == row[j + 1]) && (row[j] != 0)) {
						row[j] *= 2;
						j++;
						if (j == 1) {
							row[1] = row[2];
							row[2] = row[3];
							row[3] = 0;
						} else if (j == 2) {
							row[2] = row[3];
							row[3] = 0;
						} else if (j == 3) {
							row[3] = 0;
						}
						change = true;
						break;
					}
				}
				for (int k = 0; k != 4; k++) {
					map[k][i].setValue(row[k]);
				}
			}
			if (change) {
				random();
			}
			invalidate();
			return;
		}
		if (direct == MOVE_DOWN) {
			boolean change = false;
			for (int i = 0; i != 4; i++) {
				int[] row = new int[4];
				int a = 0;
				if (map[3][i].getValue() != 0) {
					row[a] = map[3][i].getValue();
					if (a != 0) {
						change = true;
					}
					a++;
				}
				if (map[2][i].getValue() != 0) {
					row[a] = map[2][i].getValue();
					if (a != 1) {
						change = true;
					}
					a++;
				}
				if (map[1][i].getValue() != 0) {
					row[a] = map[1][i].getValue();
					if (a != 2) {
						change = true;
					}
					a++;
				}
				if (map[0][i].getValue() != 0) {
					row[a] = map[0][i].getValue();
					if (a != 3) {
						change = true;
					}
					a++;
				}
				for (int j = 0; j != 3; j++) {
					if ((row[j] == row[j + 1]) && (row[j] != 0)) {
						row[j] *= 2;
						j++;
						if (j == 1) {
							row[1] = row[2];
							row[2] = row[3];
							row[3] = 0;
						} else if (j == 2) {
							row[2] = row[3];
							row[3] = 0;
						} else if (j == 3) {
							row[3] = 0;
						}
						change = true;
						break;
					}
				}
				for (int k = 0; k != 4; k++) {
					map[k][i].setValue(row[3 - k]);
				}
			}
			if (change) {
				random();
			}
			invalidate();
			return;
		}

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
			move(MOVE_RIGHT);
			//Toast.makeText(context, "右", Toast.LENGTH_SHORT).show();
		} else if (X < -100) {
			move(MOVE_LEFT);
			//Toast.makeText(context, "左", Toast.LENGTH_SHORT).show();
		} else if (Y > 100) {
			move(MOVE_DOWN);
			//Toast.makeText(context, "下", Toast.LENGTH_SHORT).show();
		} else if (Y < -100) {
			move(MOVE_UP);
			//Toast.makeText(context, "上", Toast.LENGTH_SHORT).show();
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
