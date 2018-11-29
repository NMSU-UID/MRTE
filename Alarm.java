package mrte.trollalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {
    //Display message to wake up.
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Wake Up!",Toast.LENGTH_LONG).show();
    }
}