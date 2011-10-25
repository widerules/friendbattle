package de.passsy.friendbattle;

import android.content.Context;
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
    }

    public TextViewFlipped(final Context context, final AttributeSet attrs) {

        super(context, attrs);
    }

    public TextViewFlipped(final Context context) {

        super(context);
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
