package de.passsy.friendbattle;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Buzzer extends RelativeLayout {

    public interface OnBuzzListener {
	public abstract void onBuzz(Buzzer btn);
    }

    private OnBuzzListener mBuzzListener;
    private Player mPlayer;
    private TextViewFlipped mPoints_txt;
    private ImageView mBackground;
    private Boolean mFlipped = false;
    private Boolean mCorrectBuzz = false;
    private Boolean mTooLateBuzz = false;

    private int mPoints = 0;
    private TextView mText_txt;

    public Buzzer(Context context) {
	super(context);
	init(context);
    }

    public Buzzer(Context context, AttributeSet attrs) {
	super(context, attrs);
	init(context);
	analyseAttributes(context, attrs);
    }

    private void init(Context context) {
	LayoutInflater.from(context).inflate(R.layout.buzzer, this, true);
	mBackground = (ImageView) findViewById(R.id.background);
	setOnTouchListener(new OnTouchListener() {

	    @Override
	    public boolean onTouch(View v, MotionEvent event) {
		final int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		    if (mBuzzListener != null && mPlayer != null) {
			mBuzzListener.onBuzz(Buzzer.this);
			int image = 0;
			if (mFlipped) {
			    if (mTooLateBuzz) {
				image = R.drawable.buzzerupdown_gray;
			    } else if (mCorrectBuzz) {
				image = R.drawable.buzzerupdown_green;
			    } else {
				image = R.drawable.buzzerupdown_red;
			    }
			} else {
			    if (mTooLateBuzz) {
				image = R.drawable.buzzerdownup_gray;
			    } else if (mCorrectBuzz) {
				image = R.drawable.buzzerdownup_green;
			    } else {
				image = R.drawable.buzzerdownup_red;
			    }
			}
			mBackground.setImageResource(image);
			setCorrectBuzz(false);
			setTooLateBuzz(false);
		    }
		    break;

		case MotionEvent.ACTION_MOVE:
		    break;

		case MotionEvent.ACTION_UP:

		    if (mFlipped) {
			mBackground.setImageResource(R.drawable.buzzerdownup);
		    } else {
			mBackground.setImageResource(R.drawable.buzzerupdown);
		    }
		    break;

		case MotionEvent.ACTION_CANCEL:
		    break;
		}

		return true;
	    }
	});

	mPoints_txt = (TextViewFlipped) findViewById(R.id.points_txt);
    }

    public void onBuzz(final View v) {
	if (mBuzzListener != null) {
	    mBuzzListener.onBuzz(this);
	}
    }

    public void setonBuzzListener(final OnBuzzListener l) {
	mBuzzListener = l;
    }

    /**
     * Analysis of the attributes set within the XML file
     * 
     * @param context
     * @param attrs
     */
    private void analyseAttributes(final Context context,
	    final AttributeSet attrs) {

	if (attrs != null) {

	    final TypedArray attributes = context.obtainStyledAttributes(attrs,
		    R.styleable.Buzzer);

	    final boolean flipped = attributes.getBoolean(
		    R.styleable.Buzzer_flipped, false);
	    if (flipped) {
		mBackground.setImageResource(R.drawable.buzzerdownup);
		mPoints_txt.setFlipped(true);
		mFlipped = true;
	    }
	    attributes.recycle();
	}
    }

    public void setText(CharSequence text) {
	mPoints_txt.setText(text);
    }

    public Player getPlayer() {
	return mPlayer;
    }

    public void setPlayer(Player player) {
	this.mPlayer = player;
    }

    public Boolean getCorrectBuzz() {
	return mCorrectBuzz;
    }

    public void setCorrectBuzz(Boolean correctBuzz) {
	this.mCorrectBuzz = correctBuzz;
    }

    public Boolean getTooLateBuzz() {
	return mTooLateBuzz;
    }

    public void setTooLateBuzz(Boolean tooLateBuzz) {
	this.mTooLateBuzz = tooLateBuzz;
    }

}
