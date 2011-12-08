package de.passsy.friendbattle.controls;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.passsy.friendbattle.R;
import de.passsy.friendbattle.data.Player;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
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
    private TextView mPoints_txt;
    private ImageView mBackground;
    private Boolean mFlipped = false;
    private Boolean mCorrectBuzz = false;
    private Boolean mTooLateBuzz = false;
    private View mColorView;
    private int mColor;
    private int mPreviousY;

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
	findViews();
	setRandomColor();
	this.setOnTouchListener(new OnTouchListener() {

	    @Override
	    public boolean onTouch(View v, MotionEvent event) {
		final int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		    mPreviousY = (int) event.getY();

		    if (mBuzzListener != null && mPlayer != null) {
			mBuzzListener.onBuzz(Buzzer.this);
			int image = 0;

			if (mTooLateBuzz) {
			    image = R.drawable.buzzerdownup_gray;
			} else if (mCorrectBuzz) {
			    image = R.drawable.buzzerdownup_green;
			} else {
			    image = R.drawable.buzzerdownup_red;
			}
			mBackground.setImageResource(image);
			setCorrectBuzz(false);
			setTooLateBuzz(false);
		    }
		    break;

		case MotionEvent.ACTION_MOVE:
		    onMove(event);
		    break;

		case MotionEvent.ACTION_UP:

		    mBackground.setImageResource(R.drawable.buzzerupdown);

		case MotionEvent.ACTION_CANCEL:
		    break;
		}

		return true;
	    }

	});

    }

    private void setRandomColor() {
	Random rand = new Random();
	List<Integer> rgb = new ArrayList<Integer>();

	final int red = (int) (Math.round(Math.random()) * 255);
	final int green = (int) (Math.round(Math.random()) * 255);
	final int blue = (int) (Math.round(Math.random()) * 255);

	rgb.add(red);
	rgb.add(green);
	rgb.add(blue);

	rgb.get((int) (Math.round(Math.random())));

	Log.v("tag", "random" + red + " " + green + " " + blue);
	if (red + green + blue == 3 * 255 || red + green + blue == 0) {
	    Log.v("tag", "wrong colors");
	    setRandomColor();
	    return;
	} else {
	    setColor(Color.rgb(red, green, blue));
	}

    }

    private void findViews() {

	mBackground = (ImageView) findViewById(R.id.background);
	mPoints_txt = (TextView) findViewById(R.id.points_txt);
	mColorView = (View) findViewById(R.id.color_view);
    }

    public void onBuzz(final View v) {
	if (mBuzzListener != null) {
	    mBuzzListener.onBuzz(this);
	}
    }

    /**
     * sets the Color of the
     * 
     * @param color
     */
    private void setColor(int color) {
	mColor = color;
	mColorView.setBackgroundColor(color);
    }

    private void setColor(Color color) {
	setColor(color.hashCode());
    }

    private int getColor() {
	return mColor;
    }

    private int isHex(int i) {
	if (i > 255) {
	    i = 255;
	}
	if (i < 0) {
	    i = 0;
	}
	return i;
    }

    public void setOnBuzzListener(final OnBuzzListener l) {
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

    @Override
    protected void dispatchDraw(Canvas canvas) {
	if (mFlipped) {

	    canvas.save();
	    float py = this.getHeight() / 2.0f;
	    float px = this.getWidth() / 2.0f;
	    canvas.rotate(180, px, py);
	    super.dispatchDraw(canvas);
	    // invalidate();

	    canvas.restore();
	} else {
	    super.dispatchDraw(canvas);
	}
    }

    private void onMove(MotionEvent event) {
	int y = (int) event.getY();
	int delta = (mPreviousY - y) * 2;
	mPreviousY = y;
	int red = 0, green = 0, blue = 0;
	blue = mColor & 0xFF;
	green = (mColor >> 8) & 0xFF;
	red = (mColor >> 16) & 0xFF;
	Log.v("tag", "before rgb:" + red + " " + green + " " + blue);
	if (delta > 0) {
	    if (red == 255 && green < 255 && blue == 0) {
		green += Math.abs(delta);
	    }
	    if (red > 0 && green == 255 && blue == 0) {
		red -= Math.abs(delta);
	    }
	    if (red == 0 && green == 255 && blue < 255) {
		blue += Math.abs(delta);
	    }
	    if (red == 0 && green > 0 && blue == 255) {
		green -= Math.abs(delta);
	    }
	    if (red < 255 && green == 0 && blue == 255) {
		red += Math.abs(delta);
	    }
	    if (red == 255 && green == 0 && blue > 0) {
		blue -= Math.abs(delta);
	    }
	} else {
	    if (red < 255 && green == 255 && blue == 0) {
		red += Math.abs(delta);
	    }
	    if (red == 255 && green > 0 && blue == 0) {
		green -= Math.abs(delta);
	    }
	    if (red == 255 && green == 0 && blue < 255) {
		blue += Math.abs(delta);
	    }
	    if (red > 0 && green == 0 && blue == 255) {
		red -= Math.abs(delta);
	    }
	    if (red == 0 && green < 255 && blue == 255) {
		green += Math.abs(delta);
	    }
	    if (red == 0 && green == 255 && blue > 0) {
		blue -= Math.abs(delta);
	    }
	}

	Log.v("tag", "rgb:" + red + green + blue);

	setColor(new Color().rgb(isHex(red), isHex(green), isHex(blue)));
	invalidate();
    }

}
