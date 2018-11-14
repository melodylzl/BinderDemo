package com.byvoid.binderserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class DictionaryManagerService extends Service {

    private Map<String,String> mMap = new HashMap<>();


    public DictionaryManagerService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IDictionaryManager.Stub() {
            @Override
            public void add(String chinese, String english) throws RemoteException {
                mMap.put(chinese, english);
                Log.d("DictionaryManager", "add new word");
            }

            @Override
            public String query(String chinese) throws RemoteException {
                return mMap.get(chinese);
            }
        };
    }
}
