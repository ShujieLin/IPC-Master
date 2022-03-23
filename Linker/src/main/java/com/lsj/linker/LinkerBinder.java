package com.lsj.linker;

import android.os.IBinder;

/**
 * File description
 *
 * @author linshujie
 * @date 2022/3/22
 */
public interface LinkerBinder extends IBinder {
    void registerObject(Object object);

    void unRegisterObject(Object object);

    final class Factory{
        private Factory(){}

        public static LinkerBinder newBinder(){
            return new LinderBinderImpl();
        }
    }
}
