package de.passsy.friendbattle;

import android.app.Application;
import android.content.Context;

public class FriendBattle extends Application {
    
    private static Context context;

    public void onCreate(){
	//super.onCreate();
	setContext(getApplicationContext());
    }

    private void setContext(Context context){
	FriendBattle.context = context;
    }
    
    /**
     * returns the Context of the Application
     * @return
     */
    public static Context getAppContext(){
	return context;
    }

}
