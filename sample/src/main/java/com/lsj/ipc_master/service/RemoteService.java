package com.lsj.ipc_master.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.codezjx.andlinker.AndLinkerBinder;
import com.lsj.linker.utils.KLog;

/**
 * File description
 *
 * @author linshujie
 * @date 2022/3/22
 */
public class RemoteService extends Service {
    private AndLinkerBinder andLinkerBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.d();
        andLinkerBinder = AndLinkerBinder.Factory.newBinder();
        andLinkerBinder.registerObject(remoteService);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        KLog.d();
        return andLinkerBinder;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        andLinkerBinder.unRegisterObject(remoteService);
    }

    private final IRemoteService remoteService = () -> {
        KLog.d(520);
        return 520;
    };

}
