package com.my2048;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	public static TextView score;
	
	private int s = -1;
	
	private ImageButton button;
	
	private GameView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = (TextView)findViewById(R.id.score);
        view = (GameView)findViewById(R.id.game);
        button = (ImageButton)findViewById(R.id.restart);
        button.setOnClickListener(this);
        score.setText(0 + "");
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.restart:
			view.score = 0;
			for (int i = 0; i != 4; i++) {
				for (int j = 0; j != 4; j++) {
					view.map[i][j].setValue(0);
				}
			}
			view.random();
			view.random();
			view.invalidate();
			score.setText(0 + "");
			break;
		}
		
	}
    
}
