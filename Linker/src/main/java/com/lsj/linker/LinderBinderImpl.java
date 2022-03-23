package com.lsj.linker;

import android.os.IBinder;
import android.os.RemoteException;

/**
 * File description
 *
 * @author linshujie
 * @date 2022/3/22
 */
public class LinderBinderImpl extends ITransfer.Stub implements LinkerBinder {
    @Override
    public IBinder asBinder() {
        return null;
    }

    @Override
    public void registerObject(Object object) {

    }

    @Override
    public void unRegisterObject(Object object) {

    }

    @Override
    public Response execute(Request request) throws RemoteException {
        return null;
    }

    @Override
    public void register(ICallback callback) throws RemoteException {

    }

    @Override
    public void unRegister(ICallback callback) throws RemoteException {

    }
}
