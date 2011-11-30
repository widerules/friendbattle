package de.passsy.friendbattle.utility;

import de.passsy.friendbattle.FriendBattle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Tools {
    
    public static void toast(final CharSequence name) {

        final Toast toast = Toast.makeText(FriendBattle.getCurrentActivityContext(), name, android.widget.Toast.LENGTH_SHORT);
        toast.show();
    }

}
