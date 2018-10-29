package com.hongri.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static com.hongri.model.MainActivity.STATIC_RECEIVER_ACTION;

/**
 * @author hongri
 */
public class MyStaticReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case STATIC_RECEIVER_ACTION:
                Toast.makeText(context, "收到静态注册广播", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
