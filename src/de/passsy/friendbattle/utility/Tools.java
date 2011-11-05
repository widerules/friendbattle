package de.passsy.friendbattle.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Tools {
    private static Context mContext = null;
    private static Activity mCurrentActivity = null;

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
    
    /**
     * sets the current Activity that is shown to the User. 
     * This function should called direct when a Activity starts.
     * @param currentActivity 
     */
    public static void setCurrentActivity(Activity currentActivity){
	mCurrentActivity = currentActivity;
	mContext = currentActivity.getApplicationContext();
    }
    
    public static void setContext(Context context){
	mContext = context;
    }
    
    public static void toast(final CharSequence name) {

        final Toast toast = Toast.makeText(mContext, name, android.widget.Toast.LENGTH_SHORT);
        toast.show();
    }

    public static Context getContext() {
        return mContext;
    }

    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }
    
    
}
