package com.brzhang.example;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by brzhang on 2017/10/15.
 * Description :
 */

public class PageData implements Parcelable {
    private String mPageName;
    private Class mClazz;

    public PageData(String mPageName, Class mClazz) {
        this.mPageName = mPageName;
        this.mClazz = mClazz;
    }

    public String getmPageName() {
        return mPageName;
    }

    public Class getmClazz() {
        return mClazz;
    }

    public void setmClazz(Class mClazz) {
        this.mClazz = mClazz;
    }

    public void setmPageName(String mPageName) {
        this.mPageName = mPageName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPageName);
        dest.writeSerializable(this.mClazz);
    }

    public PageData() {
    }

    protected PageData(Parcel in) {
        this.mPageName = in.readString();
        this.mClazz = (Class) in.readSerializable();
    }

    public static final Parcelable.Creator<PageData> CREATOR = new Parcelable.Creator<PageData>() {
        @Override
        public PageData createFromParcel(Parcel source) {
            return new PageData(source);
        }

        @Override
        public PageData[] newArray(int size) {
            return new PageData[size];
        }
    };
}
