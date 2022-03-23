package com.lsj.linker;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * File description
 *
 *
 * @author linshujie
 * @date 2022/3/23
 */
public interface SuperParcelable extends Parcelable {
    /**
     * 读取parcel内容到这个类
     * @param in
     */
    void readFromParcel(Parcel in);
}
