package com.byvoid.binderclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.byvoid.binderserver.IDictionaryManager;

public class MainActivity extends AppCompatActivity {

    private IDictionaryManager mDictionaryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setPackage("com.byvoid.binderserver");
        intent.setAction("android.intent.action.DictionaryManagerService");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        //添加一个新单词
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mDictionaryManager.add("你好", "Hello");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        //查询单词
        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String english = mDictionaryManager.query("你好");
                    Toast.makeText(MainActivity.this, english, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IDictionaryManager dictionaryManager = IDictionaryManager.Stub.asInterface(iBinder);
            try {
                mDictionaryManager = dictionaryManager;
                Toast.makeText(MainActivity.this, "connect success", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "connect failed", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
}
