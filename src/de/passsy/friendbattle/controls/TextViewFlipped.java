package de.passsy.friendbattle.controls;

import de.passsy.friendbattle.R;
import de.passsy.friendbattle.R.styleable;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewFlipped extends TextView {
    
    private Boolean mFlipped = false;

    public TextViewFlipped(final Context context, final AttributeSet attrs, final int defStyle) {

        super(context, attrs, defStyle);
        analyseAttributes(context, attrs);
    }

    public TextViewFlipped(final Context context, final AttributeSet attrs) {

        super(context, attrs);
        analyseAttributes(context, attrs);
    }

    public TextViewFlipped(final Context context) {

        super(context);
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

	if(mFlipped){
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
