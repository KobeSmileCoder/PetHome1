package com.example.sunhan.pethome1.ui.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.example.sunhan.pethome1.R;

/**
 * Created by asus on 2016/8/30.
 */
public class WaitDialog extends ProgressDialog {
    public WaitDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setProgressStyle(STYLE_SPINNER);
        setMessage(context.getString(R.string.wait_dialog_title));
    }

    public WaitDialog(Context context, int theme) {
        super(context, theme);
    }
}
