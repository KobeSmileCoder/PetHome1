package com.example.sunhan.pethome1.entity;

import android.support.annotation.StringRes;

public class ShequGridInfo {

    private @StringRes int gridIcon;
    private String gridTitle;

    public ShequGridInfo(int gridIcon, String gridTitle) {
        this.gridIcon = gridIcon;
        this.gridTitle = gridTitle;
    }

    public int getGridIcon() {
        return gridIcon;
    }

    public String getGridTitle() {
        return gridTitle;
    }

    public void setGridIcon(int gridIcon) {
        this.gridIcon = gridIcon;
    }

    public void setGridTitle(String gridTitle) {
        this.gridTitle = gridTitle;
    }
}
