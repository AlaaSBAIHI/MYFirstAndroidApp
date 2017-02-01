package com.example.redi.MyFirstAndroidApp.models.entities;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by ReDI on 11/12/2016.
 */

public class SettingItems {
    @DrawableRes
    private int slogo;

    private String sname;

    private Boolean sswitsch;

    private String sswitchtext;

    public SettingItems(@DrawableRes int slogo, @NonNull String sname, @NonNull Boolean sswitsch) {
        this.slogo = slogo;
        this.sname = sname;
        this.sswitsch = sswitsch;
    }


    public int getSlogo() {
        return slogo;
    }

    public String getSname() {
        return sname;
    }

    public Boolean getSswitsch() {
        return sswitsch;
    }

    public void setSswitsch(Boolean sswitsch) {
        this.sswitsch = sswitsch;
    }

    public String getSswitchtext() {
        if (sswitsch)
            sswitchtext = "On";
        else sswitchtext = "Off";

        return sswitchtext;
    }


}
