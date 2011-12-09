package de.passsy.friendbattle.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;
import de.passsy.friendbattle.R;

public class ButtonFlipped extends Button {

    private boolean mFlipped = false;

    public ButtonFlipped(final Context context, final AttributeSet attrs) {
	super(context, attrs);
	analyseAttributes(context, attrs);

    }

    private void analyseAttributes(final Context context,
	    final AttributeSet attrs) {
	if (attrs != null) {

	    final TypedArray attributes = context.obtainStyledAttributes(attrs,
		    R.styleable.Buzzer);

	    final boolean flipped = attributes.getBoolean(
		    R.styleable.Buzzer_flipped, false);
	    if (flipped) {
		setFlipped(true);
	    }
	    attributes.recycle();
	}

    }

    @Override
    protected void onDraw(final Canvas canvas) {

	if (mFlipped) {
	    canvas.save();
	    final float py = getHeight() / 2.0f;
	    final float px = getWidth() / 2.0f;
	    canvas.rotate(180, px, py);

	    super.onDraw(canvas);

	    canvas.restore();
	} else {
	    super.onDraw(canvas);
	}
    }

    public Boolean getFlipped() {
	return mFlipped;
    }

    public void setFlipped(final Boolean flipped) {
	mFlipped = flipped;
    }

}
