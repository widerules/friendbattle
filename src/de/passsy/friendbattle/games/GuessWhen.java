package de.passsy.friendbattle.games;

import android.view.LayoutInflater;
import de.passsy.friendbattle.R;
import de.passsy.friendbattle.Screen_TextViewsCenter;
import de.passsy.friendbattle.utility.Tools;

public class GuessWhen extends MiniGame{
    
    private Screen_TextViewsCenter mTextViews = new Screen_TextViewsCenter();
    
    public GuessWhen(){
	this.addView(mTextViews);
	mTextViews.setText("?");
    }

    @Override
    protected void showIntroductions(int seconds) {
	
    }

    @Override
    public void startGame() {
	
    }

    @Override
    public CharSequence getDescription() {
	return "Guess when it is";
    }

}
