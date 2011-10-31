package de.passsy.friendbattle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;

public class ButtonFlipped extends Button {

    private boolean mFlipped = false;


    public ButtonFlipped(Context context, AttributeSet attrs) {
	super(context, attrs);
	analyseAttributes(context, attrs);
	
    }
    private void analyseAttributes(Context context, AttributeSet attrs) {
	if (attrs != null) {

	    final TypedArray attributes = context.obtainStyledAttributes(attrs,R.styleable.Buzzer);

	    final boolean flipped = attributes.getBoolean(R.styleable.Buzzer_flipped, false);
	    if (flipped) {
		setFlipped(true);
	    }
	    attributes.recycle();
	}
	
    }

    @Override
    protected void onDraw(final Canvas canvas) {

	if(mFlipped ){
    	    canvas.save();
    	    float py = this.getHeight()/2.0f;
    	    float px = this.getWidth()/2.0f;
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
    

    public void setFlipped(Boolean flipped) {
        this.mFlipped = flipped;
    }

}
