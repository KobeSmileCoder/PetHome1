package com.example.sunhan.pethome1.ui.activities.itemActivities;

import android.view.View;
import android.widget.TextView;

import com.example.sunhan.pethome1.R;
import com.example.sunhan.pethome1.ui.activities.BaseAvtivity;
import com.example.sunhan.pethome1.ui.widget.ConnectDialog;
import com.example.sunhan.pethome1.ui.widget.MyToolbar;

public class VersionActivity extends BaseAvtivity {
    TextView update;
    ConnectDialog connectDialog;
    MyToolbar toolbar;

    @Override
    protected View getContentView() {
        return inflateView(R.layout.activity_version);
    }

    @Override
    protected void setContentViewAfter(View contentView) {

    }
}
