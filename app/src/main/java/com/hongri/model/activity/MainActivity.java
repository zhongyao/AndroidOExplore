package com.hongri.model.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.hongri.model.R;
import com.hongri.receiver.MyDynamicReceiver;

/**
 * @author hongri
 *
 *         当targetSdkVersion >= 26 (Android8.0)时：
 *         1、静态注册的广播，需要使用显式广播发送
 *         方法<1>添加intent.setPackage(getPackageName());
 *         方法<2>添加setClassName(getPackageName(),"com.hongri.receiver.MyStaticReceiver");
 *         方法<3>添加ComponentName componentName = new ComponentName(getPackageName(),"com.hongri.receiver
 *         .MyStaticReceiver");
 *         intent.setComponent(componentName);
 *
 *         2、动态注册的广播，则不受限制
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnBroadcast;
    public static final String DYNAMIC_RECEIVER_ACTION = "hongri.intent.action.ON_DYNAMIC_REQ";
    public static final String STATIC_RECEIVER_ACTION = "hongri.intent.action.ON_STATIC_REQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBroadcast = findViewById(R.id.btnBroadcast);
        btnBroadcast.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBroadcast:
                /**
                 * 静态注册广播
                 */
                //
                //Intent intent = new Intent();
                //intent.setAction(STATIC_RECEIVER_ACTION);
                ////当targetSdkVersion >= 26时，添加该项8.0系统发送隐式广播依然可以收到广播
                //intent.setPackage(getPackageName());
                //sendBroadcast(intent);

                Intent intent2 = new Intent();
                intent2.setAction(STATIC_RECEIVER_ACTION);
                intent2.setClass(MainActivity.this, MyDynamicReceiver.class);

                String packageName = MainActivity.this.getPackageName();
                Toast.makeText(MainActivity.this, "---" + packageName, Toast.LENGTH_SHORT).show();
                intent2.setClassName(getPackageName(), "com.hongri.receiver.MyStaticReceiver");
                //ComponentName componentName = new ComponentName(getPackageName(),"com.hongri.receiver
                // .MyStaticReceiver");
                //intent2.setComponent(componentName);
                sendBroadcast(intent2);

                /**
                 * 动态注册广播
                 */
                MyDynamicReceiver dynamicReceiver = new MyDynamicReceiver();
                IntentFilter filter = new IntentFilter();
                filter.addAction(DYNAMIC_RECEIVER_ACTION);
                registerReceiver(dynamicReceiver, filter);

                Intent intent_dynamic = new Intent();
                intent_dynamic.setAction(DYNAMIC_RECEIVER_ACTION);
                //intent_dynamic.setPackage(getPackageName());
                sendBroadcast(intent_dynamic);

                break;

            default:
                break;
        }
    }
}
