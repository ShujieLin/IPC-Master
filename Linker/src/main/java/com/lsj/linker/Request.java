package com.lsj.linker;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Array;

/**
 * File description
 *
 * @author linshujie
 * @date 2022/3/23
 */
@SuppressLint("ParcelCreator")
final class Request implements SuperParcelable{
    private String targetClass;
    private String methodName;
    private BaseTypeWrapper[] argsWrapper;
    //仅用于客户端
    private boolean oneWay = false;

    public Request(String targetClass, String methodName, BaseTypeWrapper[] argsWrapper) {
        this.targetClass = targetClass;
        this.methodName = methodName;
        this.argsWrapper = argsWrapper;
    }

    public Request(String targetClass, String methodName, BaseTypeWrapper[] argsWrapper,
            boolean oneWay) {
        this.targetClass = targetClass;
        this.methodName = methodName;
        this.argsWrapper = argsWrapper;
        this.oneWay = oneWay;
    }

    public Request(Parcel in) {
        targetClass = in.readString();
        methodName = in.readString();
        argsWrapper = readParcelableArray(getClass().getClassLoader(),BaseTypeWrapper.class,in);
    }

    @Override
    public void readFromParcel(Parcel in) {
        targetClass = in.readString();
        methodName = in.readString();
        readParcelableArrayFromParcel(in,argsWrapper);
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(targetClass);
        dest.writeString(methodName);
        if (flags == PARCELABLE_WRITE_RETURN_VALUE){
            writeParcelArrayToParcel(dest,argsWrapper,flags);
        }else {
            dest.writeParcelableArray(argsWrapper,flags);
        }

    }

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel source) {
            return new Request(source);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };

    /**
     * 把数据保存起来
     * @param in
     * @param values
     * @param <T>
     */
    private <T extends BaseTypeWrapper> void readParcelableArrayFromParcel(Parcel in, T[] values) {
        int n = in.readInt();
        if (n < 0)
            return;
        for (int i = 0; i < n; i++) {
            values[i].readFromParcel(in);
        }
    }

    /**
     * 写
     * @param dest
     * @param value
     * @param parceableFlags
     * @param <T>
     */
    private <T extends BaseTypeWrapper> void writeParcelArrayToParcel(Parcel dest,
            T[]  value, int parceableFlags) {
        if (value != null){
            int n = value.length;
            dest.writeInt(n);
            for (int i = 0; i < n; i++) {
                value[i].writeToParcel(dest,parceableFlags);
            }
        }else {
            dest.writeInt(-1);
        }
    }


    /**
     * 读到数组里面
     * @param classLoader
     * @param clazz
     * @param in
     * @param <T>
     * @return
     */
    private <T extends Parcelable> T[] readParcelableArray(ClassLoader classLoader,
            Class<T> clazz, Parcel in) {
        int n = in.readInt();
        if (n < 0)
            return null;
        //创建一个数组
        T[] p = (T[]) Array.newInstance(clazz, n);
        for (int i = 0; i < n; i++) {
            p[i] = in.readParcelable(classLoader);
        }
        return p;
    }

    public boolean isOneWay() {
        return oneWay;
    }
}
