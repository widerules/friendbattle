package de.passsy.friendbattle;

import de.passsy.friendbattle.utility.Tools;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class Screen_TextViewsCenter extends RelativeLayout {

    private TextViewFlipped mTop_txt;
    private TextViewFlipped mBot_txt;
    
    public Screen_TextViewsCenter(){
	super(Tools.getContext());	
	init(Tools.getContext());
    }

    public Screen_TextViewsCenter(Context context){
	super(context);
	init(context);
    }

    public Screen_TextViewsCenter(Context context, AttributeSet attrs) {
	super(context, attrs);
	init(context);
    }

    public Screen_TextViewsCenter(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
	init(context);
    }
    
    private void init(Context context) {
	LayoutInflater.from(context).inflate(R.layout.textview_center, this, true);
	findViews();
    }

    private void findViews() {
	mTop_txt = (TextViewFlipped) findViewById(R.id.textViewFlippedTop);
	mBot_txt = (TextViewFlipped) findViewById(R.id.textViewFlippedBot);
    }
    
    public void setText(CharSequence text){
	mTop_txt.setText(text);
	mBot_txt.setText(text);
    }
    
}
