package com.lsj.linker;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * File description
 *
 * @author linshujie
 * @date 2022/3/22
 */
interface ITransfer extends IInterface {
    abstract class Stub extends Binder implements ITransfer{
        private static final String DESCRIPER = "com.lsj.linker.ITransfer";
        private static int TRANSACTION_EXECUTE = FIRST_CALL_TRANSACTION + 0;
        private static int TRANSACTION_REGISTER = FIRST_CALL_TRANSACTION + 1;
        private static int TRANSACTION_UNREGISTER = FIRST_CALL_TRANSACTION + 2;

        Stub(){
            this.attachInterface(this,DESCRIPER);
        }


        private static class Proxy implements ITransfer{
            private IBinder remote;

            public Proxy(IBinder remote) {
                this.remote = remote;
            }

            @Override
            public IBinder asBinder() {
                return remote;
            }

            @Override
            public Response execute(Request request) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                Response result;

                try{
                    data.writeInterfaceToken(DESCRIPER);
                    if (request != null){
                        data.writeInt(1);
                        request.writeToParcel(data,0);
                    }else {
                        data.writeInt(0);
                    }

                    //ONEWAY模式，传输然后直接返回。
                    if (request != null && request.isOneWay()){
                        remote.transact(Stub.TRANSACTION_EXECUTE,data,null,FLAG_ONEWAY);
                    }

                    remote.transact(Stub.TRANSACTION_EXECUTE,data,reply,0);
                    reply.readException();

                    //result
                    if (reply.readInt() != 0){
                        result = Response.CREATOR.createFromParcel(reply);
                    }else {
                        result = null;
                    }

                    //request
                    if (reply.readInt() != 0){
                        request.readFromParcel(reply);
                    }
                }finally {
                    reply.recycle();
                    data.recycle();
                }

                return result;
            }

            @Override
            public void register(ICallback callback) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try{
                    data.writeInterfaceToken(DESCRIPER);
                    data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    remote.transact(Stub.TRANSACTION_REGISTER,data,reply,0);
                    reply.readException();
                }finally {
                    reply.recycle();
                    data.recycle();
                }
            }

            @Override
            public void unRegister(ICallback callback) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try {
                    data.writeInterfaceToken(DESCRIPER);
                    data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    remote.transact(Stub.TRANSACTION_UNREGISTER,data,reply,0);
                    reply.readException();
                }finally {
                    data.recycle();
                    reply.recycle();
                }
            }
        }
    }

    Response execute(Request request) throws RemoteException;
    void register(ICallback callback) throws RemoteException;
    void unRegister(ICallback callback) throws RemoteException;
}
