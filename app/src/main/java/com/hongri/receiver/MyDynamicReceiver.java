package com.hongri.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static com.hongri.model.MainActivity.DYNAMIC_RECEIVER_ACTION;

/**
 * @author hongri
 */
public class MyDynamicReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case DYNAMIC_RECEIVER_ACTION:
                Toast.makeText(context, "收到动态广播", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
