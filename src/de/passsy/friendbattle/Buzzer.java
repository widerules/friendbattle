package de.passsy.friendbattle;

import de.passsy.friendbattle.utility.Tools;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Buzzer extends RelativeLayout{
    
    public interface OnBuzzListener {
	public abstract void onBuzz(Buzzer btn);
    }
    private OnBuzzListener mBuzzListener;
    private Player mPlayer;
    private TextView mPoints_txt;
    
    public Player getPlayer() {
        return mPlayer;
    }

    public void setPlayer(Player player) {
        this.mPlayer = player;
    }

    private int mPoints = 0;
    
    public Buzzer(Context context) {
	super(context);
	init(context);
    }

    public Buzzer(Context context, AttributeSet attrs) {
	super(context, attrs);
	init(context);
    }
    
    private void init(Context context){
	LayoutInflater.from(context).inflate(R.layout.buzzer, this, true);
	setOnTouchListener(new OnTouchListener() {
	    
	    @Override
	    public boolean onTouch(View v, MotionEvent event) {
		final int action = event.getAction();
	        switch (action) {
	        case MotionEvent.ACTION_DOWN:
	            if (mBuzzListener != null && mPlayer != null) {
	        	mBuzzListener.onBuzz(Buzzer.this);
	            }
	            break;

	        case MotionEvent.ACTION_MOVE:
	            break;

	        case MotionEvent.ACTION_UP:
	            break;

	        case MotionEvent.ACTION_CANCEL:
	            break;
	        }

	        return true;
	    }
	});
	
	mPoints_txt = (TextView)findViewById(R.id.points_txt);
    }
    
    public void onBuzz(final View v) {
        if (mBuzzListener != null) {
            mBuzzListener.onBuzz(this);
        }
    }

    public void setonBuzzListener(final OnBuzzListener l) {
	mBuzzListener = l;
    }
    
    public void setText(CharSequence text){
	mPoints_txt.setText(text);
    }

}
