package de.passsy.friendbattle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class FriendBattle extends Application {
    
    private static Context mAppContext;
    private static Activity mCurrentActivity;
    private static Context mActivityContext;

    public void onCreate(){
	super.onCreate();
	setContext(getApplicationContext());
    }

    private void setContext(Context context){
	FriendBattle.mAppContext = context;
    }
    
    /**
     * returns the Context of the Application
     * @return
     */
    public static Context getAppContext(){
	return mAppContext;
    }
    
    /**
     * sets the current Activity that is shown to the User. 
     * This function should called direct when a Activity starts.
     * @param currentActivity 
     */
    public static void setCurrentActivity(Activity currentActivity){
	mCurrentActivity = currentActivity;
	mActivityContext = currentActivity.getApplicationContext();
    }
    
    public static Context getCurrentActivityContext(){
	return mActivityContext;
    }
    
    public static Activity getCurrentActivity(){
	return mCurrentActivity;
    }
    
    /**
     * 
     * @param activityClassName Activity that should started GameMenu.class
     * @param extra[] Stringarray that is sent to the new Activity
     */
    static public void StartActivity(Class<?> activityClassName,Bundle extras){
	Intent intent = new Intent(mCurrentActivity, activityClassName);
	if (extras != null){
	    intent.putExtras(extras);
	}
        mCurrentActivity.startActivity(intent);
        
    }

}
