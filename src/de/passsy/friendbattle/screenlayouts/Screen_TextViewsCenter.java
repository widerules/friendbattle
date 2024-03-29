package de.passsy.friendbattle.screenlayouts;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import de.passsy.friendbattle.R;
import de.passsy.friendbattle.controls.TextViewFlipped;

public class Screen_TextViewsCenter extends RelativeLayout {

    private TextViewFlipped mTop_txt;
    private TextViewFlipped mBot_txt;

    public Screen_TextViewsCenter(final Context context) {
	super(context);
	init(context);
    }

    public Screen_TextViewsCenter(final Context context,
	    final AttributeSet attrs) {
	super(context, attrs);
	init(context);
    }

    public Screen_TextViewsCenter(final Context context,
	    final AttributeSet attrs, final int defStyle) {
	super(context, attrs, defStyle);
	init(context);
    }

    private void init(final Context context) {
	LayoutInflater.from(context).inflate(R.layout.textview_center, this,
		true);
	findViews();
    }

    private void findViews() {
	mTop_txt = (TextViewFlipped) findViewById(R.id.textViewFlippedTop);
	mBot_txt = (TextViewFlipped) findViewById(R.id.textViewFlippedBot);
    }

    public void setText(final CharSequence text) {
	mTop_txt.setText(text);
	mBot_txt.setText(text);
    }

    /**
     * sets the Size of the TextViews
     * 
     * @param pt
     *            size in pt
     */
    public void setTextSize(final int pt) {
	mTop_txt.setTextSize(TypedValue.COMPLEX_UNIT_PT, pt);
	mBot_txt.setTextSize(TypedValue.COMPLEX_UNIT_PT, pt);
    }

}
