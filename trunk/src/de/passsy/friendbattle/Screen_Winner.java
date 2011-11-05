package de.passsy.friendbattle;

import de.passsy.friendbattle.utility.Tools;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class Screen_Winner extends RelativeLayout {
    
    private int mWinner;
    private TextViewFlipped mTop_txt;
    private TextViewFlipped mBot_txt;
    
    public Screen_Winner(){
	super(Tools.getContext());	
	init(Tools.getContext());
    }

    public Screen_Winner(Context context){
	super(context);
	init(context);
    }

    public Screen_Winner(Context context, AttributeSet attrs) {
	super(context, attrs);
	init(context);
    }

    public Screen_Winner(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
	init(context);
    }
    
    private void init(Context context) {
	LayoutInflater.from(context).inflate(R.layout.screen_winner2, this, true);
	findViews();
    }

    private void findViews() {
	mTop_txt = (TextViewFlipped) findViewById(R.id.textViewFlippedTop);
	mBot_txt = (TextViewFlipped) findViewById(R.id.textViewFlippedBot);
	
    }
    
    private void setText(CharSequence text){
	mTop_txt.setText(text);
	mBot_txt.setText(text);
    }
    
    public void setWinner(int playerNumber){
	setText("Player" + playerNumber + " won the Game");
    }
    
    

}
