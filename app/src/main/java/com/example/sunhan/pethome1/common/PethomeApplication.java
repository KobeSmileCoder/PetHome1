package com.example.sunhan.pethome1.common;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;
import com.baidu.location.BDLocation;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.uuzuche.lib_zxing.ZApplication;
import com.yolanda.nohttp.NoHttp;

import java.util.ArrayList;
import java.util.List;

public class PethomeApplication extends ZApplication {

    //此为支付插件的官方最新版本号，请在更新时留意更新说明
    private static final int PLUGINVERSION = 7;
    private static PethomeApplication appContext;
    private boolean flag = true;
    private List<BDLocation> mLocations = new ArrayList<>();
    private PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;
        //NoHttp初始化
        NoHttp.initialize(this);
        //Fresco初始化
        Fresco.initialize(this);
    }
}
