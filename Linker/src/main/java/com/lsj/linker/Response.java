package com.lsj.linker;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * File description
 *
 * @author linshujie
 * @date 2022/3/23
 */
public class Response implements SuperParcelable {
    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel source) {
            return new Response(source);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    public Response(Parcel source) {

    }

    @Override
    public void readFromParcel(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
