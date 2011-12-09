package de.passsy.friendbattle.utility;

import android.widget.Toast;
import de.passsy.friendbattle.FriendBattle;

public class Tools {
    
    public static void toast(final CharSequence name) {

        final Toast toast = Toast.makeText(FriendBattle.getCurrentActivityContext(), name, android.widget.Toast.LENGTH_SHORT);
        toast.show();
    }

}
