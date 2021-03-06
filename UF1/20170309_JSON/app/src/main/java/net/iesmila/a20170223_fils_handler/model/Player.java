package net.iesmila.a20170223_fils_handler.model;

import android.graphics.Bitmap;

/**
 * Created by BERNAT on 23/02/2017.
 */

public class Player {
    private int mId;
    private String mName;
    private String mCountry;
    private String mFlag;
    private Bitmap mFlagBitmap;

    public Player(int mId, String mName, String mCountry, String mFlag) {
        this.mId = mId;
        this.mName = mName;
        this.mCountry = mCountry;
        this.mFlag = mFlag;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getFlag() {
        return mFlag;
    }

    @Override
    public String toString() {
        return "Player{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mCountry='" + mCountry + '\'' +
                ", mFlag='" + mFlag + '\'' +
                '}';
    }

    public Bitmap getFlagBitmap() {
        return mFlagBitmap;
    }

    public void setFlagBitmap(Bitmap flagBitmap) {
        this.mFlagBitmap = flagBitmap;
    }
}

