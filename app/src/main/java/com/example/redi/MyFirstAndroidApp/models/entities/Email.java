package com.example.redi.MyFirstAndroidApp.models.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by ReDI on 13/12/2016.
 */

public class Email implements Parcelable {
    public static final Creator<Email> CREATOR = new Creator<Email>() {
        @Override
        public Email createFromParcel(Parcel source) {
            return new Email(source);
        }

        @Override
        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
    private String from;
    private String body;

    public Email(@NonNull String from, @NonNull String body) {
        this.from = from;
        this.body = body;
    }

    private Email(Parcel in) {
        this.from = in.readString();
        this.body = in.readString();
    }

    @NonNull
    public String getFrom() {
        return from;
    }

    @NonNull
    public String getBody() {
        return body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.from);
        dest.writeString(this.body);
    }
}
